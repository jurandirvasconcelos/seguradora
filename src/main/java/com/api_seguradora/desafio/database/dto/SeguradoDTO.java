package com.api_seguradora.desafio.database.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeguradoDTO {

    private String id;
    private String name;
    private String cpf;

}
