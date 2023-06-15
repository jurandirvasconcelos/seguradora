package com.api_seguradora.desafio.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "seguros")
public class Seguro {

    @Id
    private String id;

    private String seguradoId;

    private String veiculoId;

    private Double preco;

    private LocalDate dataAdesao;

}
