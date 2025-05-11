package com.scalableAssignment.movieservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.scalableAssignment.movieservice.model.Theatre;

public interface TheatreRepository extends MongoRepository<Theatre, String> {

}
