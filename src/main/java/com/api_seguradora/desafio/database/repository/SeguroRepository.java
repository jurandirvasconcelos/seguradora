package com.api_seguradora.desafio.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.api_seguradora.desafio.model.Seguro;

@Repository
public interface SeguroRepository extends MongoRepository<Seguro, String> {
}
