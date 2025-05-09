package com.scalableAssignment.movieservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scalableAssignment.movieservice.model.Movie;
import com.scalableAssignment.movieservice.service.MovieService;
import org.springframework.http.HttpHeaders;

@RestController
@RequestMapping("/movies")
public class MovieController {

	private final MovieService movieService;

	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	@GetMapping
	public ResponseEntity<List<Movie>> getAllMovies() {
		return ResponseEntity.ok(movieService.getAllMovies());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Movie> getMovieById(@PathVariable(name = "id") String id) {
		return movieService.getMovieById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Movie> addMovie(@RequestBody Movie movie,
			@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
		return ResponseEntity.ok(movieService.addMovie(movie, authHeader));
	}
}
