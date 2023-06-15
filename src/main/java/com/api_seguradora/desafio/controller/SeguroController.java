package com.api_seguradora.desafio.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_seguradora.desafio.model.Seguro;
import com.api_seguradora.desafio.service.SeguroService;

@RestController
@RequestMapping("/seguros")
public class SeguroController {

    private final SeguroService seguroService;

    public SeguroController(SeguroService seguroService) {
        this.seguroService = seguroService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seguro> getSeguroById(@PathVariable String id) {
        Seguro seguro = seguroService.getSeguroById(id);
        if (seguro != null) {
            return ResponseEntity.ok(seguro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Seguro>> listSeguros(){
        List<Seguro> seguros = seguroService.listSeguros();
        return ResponseEntity.ok(seguros);
    }

    @PostMapping
    public ResponseEntity<Seguro> saveSeguro(@RequestBody Seguro seguro) {
        Seguro novoSeguro = seguroService.saveSeguro(seguro);
        if (novoSeguro != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(novoSeguro);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Seguro> updateSeguro(@PathVariable String id, @RequestBody Seguro seguro) {
        Seguro seguroAtualizado = seguroService.updateSeguro(id, seguro);
        if (seguroAtualizado != null) {
            return ResponseEntity.ok(seguroAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeguro(@PathVariable String id) {
        boolean deletado = seguroService.deleteSeguro(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
