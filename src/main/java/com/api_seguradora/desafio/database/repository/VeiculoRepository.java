package com.api_seguradora.desafio.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.api_seguradora.desafio.model.Veiculo;

public interface VeiculoRepository extends MongoRepository<Veiculo, String>{

}
