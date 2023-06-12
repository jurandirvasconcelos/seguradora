package com.api_seguradora.desafio.service;

import java.util.List;

import com.api_seguradora.desafio.database.repository.VeiculoRepository;
import com.api_seguradora.desafio.model.Veiculo;

public class VeiculoService {

    private VeiculoRepository veiculoRepository;

    public List<Veiculo> listVeiculos() {
        return veiculoRepository.findAll();
    }
}
