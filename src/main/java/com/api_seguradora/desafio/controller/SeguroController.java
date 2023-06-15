package com.api_seguradora.desafio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api_seguradora.desafio.database.dto.SeguroDTO;
import com.api_seguradora.desafio.model.Seguro;
import com.api_seguradora.desafio.service.SeguroService;

import java.util.List;

@RestController
@RequestMapping("/seguros")
public class SeguroController {

    private final SeguroService seguroService;

    public SeguroController(SeguroService seguroService) {
        this.seguroService = seguroService;
    }

    @GetMapping
    public ResponseEntity<List<SeguroDTO>> listarSeguros() {
        List<SeguroDTO> seguros = seguroService.listarSeguros();
        return ResponseEntity.ok(seguros);
    }

    @PostMapping
    public ResponseEntity<SeguroDTO> cadastrarSeguro(@RequestBody SeguroDTO seguroDTO) {
        SeguroDTO novoSeguroDTO = seguroService.cadastrarSeguro(seguroDTO);

        if (novoSeguroDTO != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(novoSeguroDTO);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seguro> obterSeguroPorId(@PathVariable String id) {
        Seguro seguro = seguroService.obterSeguroPorId(id);
        if (seguro != null) {
            return ResponseEntity.ok(seguro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Seguro> atualizarSeguro(@PathVariable String id, @RequestBody Seguro seguro) {
        Seguro seguroAtualizado = seguroService.atualizarSeguro(id, seguro);
        if (seguroAtualizado != null) {
            return ResponseEntity.ok(seguroAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSeguro(@PathVariable String id) {
        boolean deletado = seguroService.deletarSeguro(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
