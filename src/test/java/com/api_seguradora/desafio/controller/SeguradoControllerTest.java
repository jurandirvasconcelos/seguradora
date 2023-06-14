package com.api_seguradora.desafio.controller;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.api_seguradora.desafio.model.Segurado;
import com.api_seguradora.desafio.service.SeguradoService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        Segurado segurado1 = new Segurado("1", "Luizinho", "123.456.789-00", "9875-1111");
        Segurado segurado2 = new Segurado("2", "Huguinho", "987.654.321-00", "9763-2222");
        List<Segurado> segurados = Arrays.asList(segurado1, segurado2);

        when(seguradoService.listSegurados()).thenReturn(segurados);

        mockMvc.perform(get("/segurados"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(segurados.size()))
                .andExpect(jsonPath("$[0].id").value(segurado1.getId()))
                .andExpect(jsonPath("$[0].name").value(segurado1.getName()))
                .andExpect(jsonPath("$[0].cpf").value(segurado1.getCpf()))
                .andExpect(jsonPath("$[0].phone").value(segurado1.getPhone()))
                .andExpect(jsonPath("$[1].id").value(segurado2.getId()))
                .andExpect(jsonPath("$[1].name").value(segurado2.getName()))
                .andExpect(jsonPath("$[1].cpf").value(segurado2.getCpf()))
                .andExpect(jsonPath("$[1].phone").value(segurado2.getPhone()));

        verify(seguradoService, times(1)).listSegurados();
    }

    @Test
    void shouldSaveSegurado() {
        // Dados de entrada
        Segurado segurado = new Segurado("1", "Zezinho", "123.456.789-00", "9653-1234");

        // Dados esperados
        Segurado novoSegurado = new Segurado("1", "Zezinho", "123.456.789-00", "9653-1234");

        // Mock do serviço
        when(seguradoService.saveSegurado(segurado)).thenReturn(novoSegurado);

        // Executar a requisição
        ResponseEntity<Segurado> response = seguradoController.saveSegurado(segurado);

        // Verificar o resultado
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(novoSegurado, response.getBody());

        // Verificar se o método do serviço foi chamado corretamente
        verify(seguradoService, times(1)).saveSegurado(segurado);
    }

    @Test
    void shouldUpdateSegurado(){
        String id = "1";
        Segurado segurado = new Segurado("1", "Zezinho Primeiro", "123.456.789-00", "9653-1234");
        Segurado seguradoAtualizado = new Segurado("1", "Zezinho Segundo", "123.456.789-00", "9653-1234");

        when(seguradoService.updateSegurado(id, segurado)).thenReturn(seguradoAtualizado);

        ResponseEntity<Segurado> response = seguradoController.updateSegurado(id, segurado);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(seguradoAtualizado, response.getBody());
    }

}