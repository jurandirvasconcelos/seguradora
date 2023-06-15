package com.api_seguradora.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_seguradora.desafio.database.dto.SeguradoDTO;
import com.api_seguradora.desafio.database.dto.SeguroDTO;
import com.api_seguradora.desafio.database.dto.VeiculoDTO;
import com.api_seguradora.desafio.database.repository.SeguroRepository;
import com.api_seguradora.desafio.model.Segurado;
import com.api_seguradora.desafio.model.Seguro;
import com.api_seguradora.desafio.model.Veiculo;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeguroService {

    private final SeguroRepository seguroRepository;
    private final SeguradoService seguradoService;
    private final VeiculoService veiculoService;

    @Autowired
    public SeguroService(SeguroRepository seguroRepository, SeguradoService seguradoService,
            VeiculoService veiculoService) {
        this.seguroRepository = seguroRepository;
        this.seguradoService = seguradoService;
        this.veiculoService = veiculoService;
    }

    public List<SeguroDTO> listarSeguros() {
        List<Seguro> seguros = seguroRepository.findAll();
        List<SeguroDTO> segurosDTO = new ArrayList<>();

        for (Seguro seguro : seguros) {
            SeguradoDTO seguradoDTO = seguradoService.obterSeguradoDTO(seguro.getSeguradoId());
            VeiculoDTO veiculoDTO = veiculoService.obterVeiculoDTO(seguro.getVeiculoId());

            SeguroDTO seguroDTO = new SeguroDTO(seguro, seguradoDTO, veiculoDTO);
            segurosDTO.add(seguroDTO);
        }

        return segurosDTO;
    }

    public SeguroDTO cadastrarSeguro(SeguroDTO seguroDTO) {
        Seguro seguro = new Seguro();
        seguro.setPreco(seguroDTO.getPreco());
        seguro.setDataAdesao(seguroDTO.getDataAdesao());

        // Obter o Segurado pelo ID
        SeguradoDTO seguradoDTO = seguradoService.obterSeguradoDTO(seguroDTO.getSeguradoId());
        Segurado segurado = new Segurado();
        segurado.setId(seguradoDTO.getId());
        segurado.setName(seguradoDTO.getName());
        segurado.setCpf(seguradoDTO.getCpf());
        seguro.setSegurado(segurado);

        // Obter o Veículo pelo ID
        VeiculoDTO veiculoDTO = veiculoService.obterVeiculoDTO(seguroDTO.getVeiculoId());
        Veiculo veiculo = new Veiculo();
        veiculo.setId(veiculoDTO.getId());
        veiculo.setMarca(veiculoDTO.getMarca());
        veiculo.setModelo(veiculoDTO.getModelo());
        veiculo.setPlaca(veiculoDTO.getPlaca());
        seguro.setVeiculo(veiculo);

        Seguro seguroSalvo = seguroRepository.save(seguro);

        return new SeguroDTO(seguroSalvo, seguradoDTO, veiculoDTO);
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
