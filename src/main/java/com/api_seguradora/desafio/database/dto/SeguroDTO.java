package com.api_seguradora.desafio.database.dto;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import com.api_seguradora.desafio.model.Seguro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeguroDTO {

    @Id
    private String id;
    private String seguradoId;
    private String veiculoId;
    private Double preco;
    private LocalDate dataAdesao;

    public SeguroDTO(Seguro seguro, SeguradoDTO seguradoDTO, VeiculoDTO veiculoDTO) {
    }

}
