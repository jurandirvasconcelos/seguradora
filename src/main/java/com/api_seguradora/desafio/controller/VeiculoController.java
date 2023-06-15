package com.api_seguradora.desafio.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_seguradora.desafio.model.Veiculo;
import com.api_seguradora.desafio.service.VeiculoService;

@RestController
@RequestMapping("veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> getVeiculos() {
        List<Veiculo> veiculos = veiculoService.listVeiculos();
        if (!veiculos.isEmpty()) {
            return ResponseEntity.ok(veiculos);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> getVeiculoById(@PathVariable String id) {
        Veiculo veiculo = veiculoService.getVeiculoById(id);
        if (veiculo != null) {
            return ResponseEntity.ok(veiculo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Veiculo> saveVeiculo(@RequestBody Veiculo veiculo) {
        Veiculo novoVeiculo = veiculoService.saveVeiculo(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVeiculo);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Veiculo> updateVeiculo(@PathVariable String id, @RequestBody Veiculo veiculo) {
        Veiculo veiculoAtualizado = veiculoService.updateVeiculo(id, veiculo);
        if (veiculoAtualizado != null) {
            return ResponseEntity.ok(veiculoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

     @DeleteMapping("/{id}")
     @Transactional
    public ResponseEntity<Void> deleteVeiculo(@PathVariable String id) {
        boolean deletado = veiculoService.deleteVeiculo(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}