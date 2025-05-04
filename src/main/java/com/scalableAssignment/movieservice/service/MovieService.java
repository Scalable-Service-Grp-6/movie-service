package com.scalableAssignment.movieservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.scalableAssignment.movieservice.model.Movie;
import com.scalableAssignment.movieservice.repository.MovieRepository;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(String id) {
        return movieRepository.findById(id);
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }
}
