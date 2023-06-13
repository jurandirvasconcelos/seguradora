package com.api_seguradora.desafio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.api_seguradora.desafio.database.repository.VeiculoRepository;
import com.api_seguradora.desafio.model.Veiculo;

public class VeiculoServiceTest {

    @Mock
    private VeiculoRepository veiculoRepository;

    @InjectMocks
    private VeiculoService veiculoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldShowAllVeiculos() {
        List<Veiculo> veiculos = new ArrayList<>();
        veiculos.add(new Veiculo("1", "Ford", "KA", "ABC-1234"));
        veiculos.add(new Veiculo("2", "Honda", "Civic", "def-9876"));

        when(veiculoRepository.findAll()).thenReturn(veiculos);

        List<Veiculo> resultado = veiculoService.listVeiculos();

        assertEquals(2, resultado.size());
        verify(veiculoRepository, times(1)).findAll();
    }

    @Test
    void shouldShowVeiculoById() {
        String id = "1";
        Veiculo veiculo = new Veiculo();
        veiculo.setId(id);

        when(veiculoRepository.findById(id)).thenReturn(Optional.of(veiculo));

        Veiculo resultado = veiculoService.getVeiculoById(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        verify(veiculoRepository, times(1)).findById(id);
    }
}
