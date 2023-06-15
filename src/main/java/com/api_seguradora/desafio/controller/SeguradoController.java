package com.api_seguradora.desafio.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_seguradora.desafio.model.Segurado;
import com.api_seguradora.desafio.service.SeguradoService;

@RestController
@RequestMapping("segurados")
public class SeguradoController {

    private final SeguradoService seguradoService;

    public SeguradoController(SeguradoService seguradoService) {
        this.seguradoService = seguradoService;
    }

    @GetMapping
    public ResponseEntity<List<Segurado>> getSegurados() {
        List<Segurado> segurados = seguradoService.listSegurados();
        if (!segurados.isEmpty()) {
            return ResponseEntity.ok(segurados);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Segurado> getSeguradoById(@PathVariable String id) {
        Segurado segurado = seguradoService.getSeguradoById(id);
        if (segurado != null) {
            return ResponseEntity.ok(segurado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Segurado> saveSegurado(@RequestBody Segurado segurado) {
        Segurado novoSegurado = seguradoService.saveSegurado(segurado);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoSegurado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Segurado> updateSegurado(@PathVariable String id, @RequestBody Segurado segurado) {
        Segurado seguradoAtualizado = seguradoService.updateSegurado(id, segurado);
        if (seguradoAtualizado != null) {
            return ResponseEntity.ok(seguradoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}