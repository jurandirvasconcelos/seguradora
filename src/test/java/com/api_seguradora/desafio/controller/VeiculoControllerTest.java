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
        Veiculo veiculo1 = new Veiculo("1", "Ford", "Ka", "ABC-111", 2023);
        Veiculo veiculo2 = new Veiculo("2", "BMW", "NãoSei", "EFG-222", 2020);
        List<Veiculo> veiculos = Arrays.asList(veiculo1, veiculo2);

        when(veiculoService.listVeiculos()).thenReturn(veiculos);

        mockMvc.perform(get("/veiculos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(veiculos.size()))
                .andExpect(jsonPath("$[0].id").value(veiculo1.getId()))
                .andExpect(jsonPath("$[0].brand").value(veiculo1.getBrand()))
                .andExpect(jsonPath("$[0].model").value(veiculo1.getModel()))
                .andExpect(jsonPath("$[0].plate").value(veiculo1.getPlate()))
                .andExpect(jsonPath("$[0].year").value(veiculo1.getYear()))
                .andExpect(jsonPath("$[1].id").value(veiculo2.getId()))
                .andExpect(jsonPath("$[1].brand").value(veiculo2.getBrand()))
                .andExpect(jsonPath("$[1].model").value(veiculo2.getModel()))
                .andExpect(jsonPath("$[1].plate").value(veiculo2.getPlate()))
                .andExpect(jsonPath("$[1].year").value(veiculo2.getYear()));

        verify(veiculoService, times(1)).listVeiculos();
    }

    @Test
    void shouldGetVeiculoById() {
        String id = "1";
        Veiculo veiculo = new Veiculo("1", "Ford", "Ka", "ABC-111", 2023);

        when(veiculoService.getVeiculoById(id)).thenReturn(veiculo);

        ResponseEntity<Veiculo> response = veiculoController.getVeiculoById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(veiculo, response.getBody());
    }

    @Test
    void shouldSaveVeiculo() {
        // Dados de entrada
        Veiculo veiculo = new Veiculo("1", "Ford", "Ka", "ABC-111", 2023);

        // Dados esperados
        Veiculo novoVeiculo = new Veiculo("2", "BMW", "NãoSei", "EFG-222", 2020);

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
        Veiculo veiculo = new Veiculo("1", "Ford", "Ka", "ABC-111", 2022);
        Veiculo veiculoAtualizado = new Veiculo("1", "BMW", "NãoSei", "CBA-000", 1800);

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