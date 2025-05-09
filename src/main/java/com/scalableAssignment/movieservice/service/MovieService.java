package com.scalableAssignment.movieservice.service;

import org.springframework.http.*;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.scalableAssignment.movieservice.model.Movie;
import com.scalableAssignment.movieservice.repository.MovieRepository;

@Service
public class MovieService {

	
	@Value("${use_auth}")
    private boolean useAuth;
	
	@Value("${auth_url}")
    private String auth_url;
	
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

    public Movie addMovie(Movie movie, String token) {
    	if(useAuth) {
    		RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", token);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            try {
                ResponseEntity<String> response = restTemplate.exchange("http://"+auth_url+"/users/verify/user?role=admin", HttpMethod.GET, entity, String.class);
                if (response.getStatusCode() != HttpStatus.OK) {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not authorized to add movies");
                }
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not authorized to add movies");
            }
    	}
        return movieRepository.save(movie);
    }
}
