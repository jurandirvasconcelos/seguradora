package com.api_seguradora.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_seguradora.desafio.database.repository.SeguroRepository;
import com.api_seguradora.desafio.model.Seguro;
import java.util.List;

@Service
public class SeguroService {

    private final SeguroRepository seguroRepository;

    @Autowired
    public SeguroService(SeguroRepository seguroRepository) {
        this.seguroRepository = seguroRepository;
    }

    public Seguro cadastraSeguro(Seguro seguro) {
        return seguroRepository.save(seguro);
    }

    public List<Seguro> listarSeguros() {
        return seguroRepository.findAll();
    }

    public Seguro obterSeguroPorId(String id) {
        return seguroRepository.findById(id).orElse(null);
    }

    public Seguro atualizarSeguro(String id, Seguro seguro) {
        if (seguroRepository.existsById(id)) {
            seguro.setId(id);
            return seguroRepository.save(seguro);
        }
        return null;
    }

    public boolean deletarSeguro(String id) {
        if (seguroRepository.existsById(id)) {
            seguroRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
