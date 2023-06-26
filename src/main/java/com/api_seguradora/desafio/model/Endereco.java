package com.api_seguradora.desafio.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Endereco {

    private String street;
    private String number;
    private String district;
    private String city;
    private String state;
    private String zipCode;

    private Segurado segurado;

}
