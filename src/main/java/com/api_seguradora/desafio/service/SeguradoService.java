package com.api_seguradora.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_seguradora.desafio.database.repository.SeguradoRepository;
import com.api_seguradora.desafio.model.Segurado;

@Service
public class SeguradoService {

    private SeguradoRepository seguradoRepository;

    @Autowired
    public SeguradoService(SeguradoRepository seguradoRepository) {
        this.seguradoRepository = seguradoRepository;
    }

    public List<Segurado> listSegurados() {
        return seguradoRepository.findAll();
    }

    public Segurado getSeguradoById(String id) {
        return seguradoRepository.findById(id).orElse(null);
    }

    public Segurado saveSegurado(Segurado segurado) {
        return seguradoRepository.save(segurado);
    }

    
}
