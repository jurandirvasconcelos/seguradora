package com.api_seguradora.desafio.database.dto;

import java.time.LocalDate;

import com.api_seguradora.desafio.model.Seguro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeguroDTO {

    private String id;
    private Double preco;
    private LocalDate dataAdesao;
    private String seguradoId;
    private String seguradoName;
    private String seguradoCpf;
    private String veiculoId;
    private String veiculoMarca;
    private String veiculoModelo;
    private String veiculoPlaca;

    public SeguroDTO(Seguro seguro, SeguradoDTO seguradoDTO, VeiculoDTO veiculoDTO) {
    }

}
