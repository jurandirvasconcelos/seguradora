package com.api_seguradora.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_seguradora.desafio.database.dto.SeguradoDTO;
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

    public Segurado updateSegurado(String id, Segurado segurado) {
        if (seguradoRepository.existsById(id)) {
            segurado.setId(id);
            return seguradoRepository.save(segurado);
        }
        return null;
    }

    public boolean deleteSegurado(String id) {
        if (seguradoRepository.existsById(id)) {
            seguradoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public SeguradoDTO obterSeguradoDTO(String id) {
        Segurado segurado = seguradoRepository.findById(id).orElse(null);
        if (segurado != null) {
            return new SeguradoDTO(segurado.getId(), segurado.getName(), segurado.getCpf());
        }
        return null;
    }

}
