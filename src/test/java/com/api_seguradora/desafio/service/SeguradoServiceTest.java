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

import com.api_seguradora.desafio.database.repository.SeguradoRepository;
import com.api_seguradora.desafio.model.Segurado;

public class SeguradoServiceTest {

    @Mock
    private SeguradoRepository seguradoRepository;

    @InjectMocks
    private SeguradoService seguradoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldShowAllSegurados() {
        List<Segurado> segurados = new ArrayList<>();
        segurados.add(new Segurado("1", "Huguinho", "12345678900", "9875-1111"));
        segurados.add(new Segurado("2", "Zezinho", "98765432100", "9763-2222"));

        when(seguradoRepository.findAll()).thenReturn(segurados);

        List<Segurado> resultado = seguradoService.listSegurados();

        assertEquals(2, resultado.size());
        verify(seguradoRepository, times(1)).findAll();
    }

    @Test
    void shouldShowSeguradoById() {
        String id = "1";
        Segurado segurado = new Segurado();
        segurado.setId(id);

        when(seguradoRepository.findById(id)).thenReturn(Optional.of(segurado));

        Segurado resultado = seguradoService.getSeguradoById(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        verify(seguradoRepository, times(1)).findById(id);
    }

    @Test
    void shouldSaveSegurado(){
        Segurado segurado = new Segurado("1", "Luizinho", "111.222.333-222", "9876-5432");

        when(seguradoRepository.save(segurado)).thenReturn(segurado);

        Segurado resultado = seguradoService.saveSegurado(segurado);

        assertNotNull(resultado);
        verify(seguradoRepository, times(1)).save(segurado);
    }
}
