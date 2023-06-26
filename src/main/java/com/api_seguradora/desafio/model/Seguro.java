package com.api_seguradora.desafio.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TypeAlias("Seguro")
@Document(collection = "seguros")
public class Seguro {

    @Id
    private String id;

    private Double preco;
    private LocalDate dataAdesao;

    private String idSegurado;
    private String idVeiculo;

}
