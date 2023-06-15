package com.api_seguradora.desafio.controller;

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

import com.api_seguradora.desafio.model.Veiculo;
import com.api_seguradora.desafio.service.VeiculoService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class VeiculoControllerTest {
    private MockMvc mockMvc;

    @Mock
    private VeiculoService veiculoService;

    @InjectMocks
    private VeiculoController veiculoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(veiculoController).build();
    }

    @Test
    void shouldShowAllVeiculos() throws Exception {
        Veiculo veiculo1 = new Veiculo("1", "Ford", "Ka", "ABC-111");
        Veiculo veiculo2 = new Veiculo("2", "BMW", "NãoSei", "EFG-222");
        List<Veiculo> veiculos = Arrays.asList(veiculo1, veiculo2);

        when(veiculoService.listVeiculos()).thenReturn(veiculos);

        mockMvc.perform(get("/veiculos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(veiculos.size()))
                .andExpect(jsonPath("$[0].id").value(veiculo1.getId()))
                .andExpect(jsonPath("$[0].marca").value(veiculo1.getMarca()))
                .andExpect(jsonPath("$[0].modelo").value(veiculo1.getModelo()))
                .andExpect(jsonPath("$[0].placa").value(veiculo1.getPlaca()))
                .andExpect(jsonPath("$[1].id").value(veiculo2.getId()))
                .andExpect(jsonPath("$[1].marca").value(veiculo2.getMarca()))
                .andExpect(jsonPath("$[1].modelo").value(veiculo2.getModelo()))
                .andExpect(jsonPath("$[1].placa").value(veiculo2.getPlaca()));

        verify(veiculoService, times(1)).listVeiculos();
    }

    @Test
    void shouldGetVeiculoById() {
        String id = "1";
        Veiculo veiculo = new Veiculo("1", "Ford", "Ka", "ABC-111");

        when(veiculoService.getVeiculoById(id)).thenReturn(veiculo);

        ResponseEntity<Veiculo> response = veiculoController.getVeiculoById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(veiculo, response.getBody());
    }

    @Test
    void shouldSaveVeiculo() {
        // Dados de entrada
        Veiculo veiculo = new Veiculo("1", "Ford", "Ka", "ABC-111");

        // Dados esperados
        Veiculo novoVeiculo = new Veiculo("2", "BMW", "NãoSei", "EFG-222");

        // Mock do serviço
        when(veiculoService.saveVeiculo(veiculo)).thenReturn(novoVeiculo);

        // Executar a requisição
        ResponseEntity<Veiculo> response = veiculoController.saveVeiculo(veiculo);

        // Verificar o resultado
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(novoVeiculo, response.getBody());

        // Verificar se o método do serviço foi chamado corretamente
        verify(veiculoService, times(1)).saveVeiculo(veiculo);
    }

    @Test
    void shouldUpdateVeiculoById() {
        String id = "1";
        Veiculo veiculo = new Veiculo("1", "Ford", "Ka", "ABC-111");
        Veiculo veiculoAtualizado = new Veiculo("1", "BMW", "NãoSei", "CBA-000");

        when(veiculoService.updateVeiculo(id, veiculo)).thenReturn(veiculoAtualizado);

        ResponseEntity<Veiculo> response = veiculoController.updateVeiculo(id, veiculo);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(veiculoAtualizado, response.getBody());
    }

    @Test
    void shouldDeleteVeiculoById() {
        String id = "1";

        when(veiculoService.deleteVeiculo(id)).thenReturn(true);

        ResponseEntity<Void> response = veiculoController.deleteVeiculo(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

}