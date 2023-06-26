package com.api_seguradora.desafio.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "segurados")
public class Segurado {

    @Id
    private String id;

    private String name;
    private String cpf;
    private String phone;

    private Endereco address;
}
