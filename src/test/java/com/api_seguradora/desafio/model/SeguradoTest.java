package com.api_seguradora.desafio.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.api_seguradora.desafio.database.repository.SeguradoRepository;
import com.api_seguradora.desafio.service.SeguradoService;

public class SeguradoTest {
    
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
        segurados.add(new Segurado("1", "Huguinho", "12345678900"));
        segurados.add(new Segurado("2", "Zezinho", "98765432100"));

        when(seguradoRepository.findAll()).thenReturn(segurados);

        List<Segurado> resultado = seguradoService.listSegurados();

        assertEquals(2, resultado.size());
        verify(seguradoRepository, times(1)).findAll();
}  
}
