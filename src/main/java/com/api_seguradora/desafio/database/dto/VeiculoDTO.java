package com.api_seguradora.desafio.database.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoDTO {

    private String id;
    private String marca;
    private String modelo;
    private String placa;

    public VeiculoDTO(String id, String marca, String placa) {
    }
}
