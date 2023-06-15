package com.api_seguradora.desafio.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// import com.api_seguradora.desafio.database.repository.copySeguroView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "segurado")
public class Segurado {

    @Id
    private String id;

    private String name;

    private String cpf;

    private String phone;

}
