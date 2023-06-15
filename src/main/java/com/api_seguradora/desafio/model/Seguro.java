package com.api_seguradora.desafio.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "seguros")
public class Seguro {
    
    @Id
    private String id;

    private double preco;
    private LocalDate dataAdesao;
    private String seguradoId;
    private String veiculoId;

    public void setSegurado(Segurado segurado) {
    }

    public void setVeiculo(Veiculo veiculo) {
    }

}
