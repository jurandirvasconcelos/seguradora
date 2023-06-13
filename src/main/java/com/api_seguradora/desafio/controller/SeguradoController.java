package com.api_seguradora.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    public List<Segurado> getSegurados() {
        return seguradoService.listSegurados();
    }

}