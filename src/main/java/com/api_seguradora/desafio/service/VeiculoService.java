package com.api_seguradora.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_seguradora.desafio.database.dto.VeiculoDTO;
import com.api_seguradora.desafio.database.repository.VeiculoRepository;
import com.api_seguradora.desafio.model.Veiculo;

@Service
public class VeiculoService {

    private VeiculoRepository veiculoRepository;

    @Autowired
    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public List<Veiculo> listVeiculos() {
        return veiculoRepository.findAll();
    }

    public Veiculo getVeiculoById(String id) {
        return veiculoRepository.findById(id).orElse(null);
    }

    public Veiculo saveVeiculo(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public Veiculo updateVeiculo(String id, Veiculo veiculo) {
        if (veiculoRepository.existsById(id)) {
            veiculo.setId(id);
            return veiculoRepository.save(veiculo);
        }
        return null;
    }

    public boolean deleteVeiculo(String id) {
        if (veiculoRepository.existsById(id)) {
            veiculoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public VeiculoDTO obterVeiculoDTO(String id) {
        Veiculo veiculo = veiculoRepository.findById(id).orElse(null);
        if (veiculo != null) {
            return new VeiculoDTO(veiculo.getId(), veiculo.getMarca(), veiculo.getPlaca());
        }
        return null;
    }
}
