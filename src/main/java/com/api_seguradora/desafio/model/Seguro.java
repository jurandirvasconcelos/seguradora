package com.api_seguradora.desafio.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Document(collection = "seguros")
public class Seguro {

    @Id
    private String id;

    private String seguradoId;

    private String veiculoId;

    private Double preco;

    private LocalDate dataAdesao;

    public Seguro() {
    }

    public Seguro(String seguradoId, String veiculoId, Double preco, LocalDate dataAdesao) {
        this.seguradoId = seguradoId;
        this.veiculoId = veiculoId;
        this.preco = preco;
        this.dataAdesao = dataAdesao;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeguradoId() {
        return seguradoId;
    }

    public void setSeguradoId(String seguradoId) {
        this.seguradoId = seguradoId;
    }

    public String getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(String veiculoId) {
        this.veiculoId = veiculoId;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public LocalDate getDataAdesao() {
        return dataAdesao;
    }

    public void setDataAdesao(LocalDate dataAdesao) {
        this.dataAdesao = dataAdesao;
    }

    public void setSegurado(Segurado segurado) {
    }

    public void setVeiculo(Veiculo veiculo) {
    }
}
