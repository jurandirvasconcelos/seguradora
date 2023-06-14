package com.api_seguradora.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_seguradora.desafio.model.Segurado;
import com.api_seguradora.desafio.service.SeguradoService;

@RestController
@RequestMapping("segurados")
public class SeguradoController {

    private final SeguradoService seguradoService;

    @Autowired
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

    @PostMapping
    @Transactional
    public ResponseEntity<Segurado> saveSegurado(@RequestBody Segurado segurado) {
        Segurado novoSegurado = seguradoService.saveSegurado(segurado);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoSegurado);
    }

}