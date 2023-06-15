package com.api_seguradora.desafio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api_seguradora.desafio.database.repository.SeguradoRepository;
import com.api_seguradora.desafio.database.repository.SeguroRepository;
import com.api_seguradora.desafio.database.repository.VeiculoRepository;
import com.api_seguradora.desafio.model.Segurado;
import com.api_seguradora.desafio.model.Seguro;
import com.api_seguradora.desafio.model.Veiculo;

@Service
public class SeguroService {

    private final SeguroRepository seguroRepository;
    private final SeguradoRepository seguradoRepository;
    private final VeiculoRepository veiculoRepository;

    public SeguroService(SeguroRepository seguroRepository, SeguradoRepository seguradoRepository, VeiculoRepository veiculoRepository) {
        this.seguroRepository = seguroRepository;
        this.seguradoRepository = seguradoRepository;
        this.veiculoRepository = veiculoRepository;
    }

    public Seguro getSeguroById(String id) {
        return seguroRepository.findById(id).orElse(null);
    }

    public List<Seguro> listSeguros(){
        return seguroRepository.findAll();
    }

    public Seguro saveSeguro(Seguro seguro) {
        Segurado segurado = seguradoRepository.findById(seguro.getSeguradoId()).orElse(null);
        Veiculo veiculo = veiculoRepository.findById(seguro.getVeiculoId()).orElse(null);

        if (segurado != null && veiculo != null) {
            seguro.setSegurado(segurado);
            seguro.setVeiculo(veiculo);
            return seguroRepository.save(seguro);
        }
        return null;
    }

    public Seguro updateSeguro(String id, Seguro seguro) {
        if (seguroRepository.existsById(id)) {
            Segurado segurado = seguradoRepository.findById(seguro.getSeguradoId()).orElse(null);
            Veiculo veiculo = veiculoRepository.findById(seguro.getVeiculoId()).orElse(null);

            if (segurado != null && veiculo != null) {
                seguro.setId(id);
                seguro.setSegurado(segurado);
                seguro.setVeiculo(veiculo);
                return seguroRepository.save(seguro);
            }
        }
        return null;
    }

    public boolean deleteSeguro(String id) {
        if (seguroRepository.existsById(id)) {
            seguroRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
