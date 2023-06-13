package com.api_seguradora.desafio.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.api_seguradora.desafio.model.Segurado;
import com.api_seguradora.desafio.service.SeguradoService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SeguradoControllerTest {
    private MockMvc mockMvc;

    @Mock
    private SeguradoService seguradoService;

    @InjectMocks
    private SeguradoController seguradoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(seguradoController).build();
    }

    @Test
    void shouldShowAllSegurados() throws Exception {
        Segurado segurado1 = new Segurado("1", "Luizinho", "12345678900");
        Segurado segurado2 = new Segurado("2", "Huguinho", "98765432100");
        List<Segurado> segurados = Arrays.asList(segurado1, segurado2);

        when(seguradoService.listSegurados()).thenReturn(segurados);

        mockMvc.perform(get("/segurados"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(segurados.size()))
                .andExpect(jsonPath("$[0].id").value(segurado1.getId()))
                .andExpect(jsonPath("$[0].name").value(segurado1.getName()))
                .andExpect(jsonPath("$[0].cpf").value(segurado1.getCpf()))
                .andExpect(jsonPath("$[1].id").value(segurado2.getId()))
                .andExpect(jsonPath("$[1].name").value(segurado2.getName()))
                .andExpect(jsonPath("$[1].cpf").value(segurado2.getCpf()));

        verify(seguradoService, times(1)).listSegurados();
    }


}