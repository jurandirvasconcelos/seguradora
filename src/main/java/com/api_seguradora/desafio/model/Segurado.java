package com.api_seguradora.desafio.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "segurados")
public class Segurado {

    private String id;

    private String name;

    private String cpf;
    
}
