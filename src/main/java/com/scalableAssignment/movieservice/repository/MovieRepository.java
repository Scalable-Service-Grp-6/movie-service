package com.scalableAssignment.movieservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.scalableAssignment.movieservice.model.Movie;

public interface MovieRepository extends MongoRepository<Movie, String> {
}