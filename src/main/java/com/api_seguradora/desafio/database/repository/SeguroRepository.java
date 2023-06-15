package com.api_seguradora.desafio.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.api_seguradora.desafio.model.Seguro;

public interface SeguroRepository extends MongoRepository<Seguro, String>{
    
}
