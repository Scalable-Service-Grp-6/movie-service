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
import com.scalableAssignment.movieservice.model.Theatre;
import com.scalableAssignment.movieservice.service.MovieService;
import com.scalableAssignment.movieservice.service.TheatreService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

@RestController
@RequestMapping("/movies")
@Slf4j
public class MovieController {

	@Autowired
	private  MovieService movieService;
	@Autowired
	private  TheatreService theatreService;	
	
	@PostMapping("/addTheatre")
	public Theatre addTheatre(@RequestBody Theatre theatre) {
		log.info("addTheatre() start...");
	    return theatreService.addTheatre(theatre);
	}
	
    @GetMapping("/theaters")
    public List<Theatre> getAllTheatres() {
    	log.info("getAllTheatres() start...");
        return theatreService.getAllTheatres();
    }
	@GetMapping
	public ResponseEntity<List<Movie>> getAllMovies() {
		log.info("getAllMovies() start...");
		return ResponseEntity.ok(movieService.getAllMovies());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Movie> getMovieById(@PathVariable(name = "id") String id) {
		log.info("getMovieById() start with movieId:"+id);
		return movieService.getMovieById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Movie> addMovie(@RequestBody Movie movie,
			@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
		log.info("addMovie() start with movieId:"+movie.getId());
		return ResponseEntity.ok(movieService.addMovie(movie, authHeader));
	}
}
