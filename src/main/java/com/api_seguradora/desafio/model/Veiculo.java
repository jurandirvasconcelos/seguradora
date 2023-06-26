package com.api_seguradora.desafio.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "veiculos")
public class Veiculo {

    @Id
    private String id;

    private String brand;
    private String model;
    private String plate;
    private Integer year;

}
