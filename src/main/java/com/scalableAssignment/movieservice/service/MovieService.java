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

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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
    	log.info("getAllMovies() service start...");
    	log.info("Fetching Movies details...");
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(String id) {
    	log.info("getMovieById() service start...");
    	log.info("Fetching Movies details with id:"+id);
        return movieRepository.findById(id);
    }

    public Movie addMovie(Movie movie, String token) {
    	log.info("addMovie() service start...");
    	if(useAuth) {
    		log.info("Authenticating Token...");
    		RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", token);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            try {
                ResponseEntity<String> response = restTemplate.exchange("http://"+auth_url+"/users/verify/user?role=admin", HttpMethod.GET, entity, String.class);
                log.info("Auth Successful...");
                if (response.getStatusCode() != HttpStatus.OK) {
                	log.error("You are not authorized to add movies");
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not authorized to add movies");
                }
            } catch (Exception e) {
            	log.error("You are not authorized to add movies");
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not authorized to add movies");
            }
    	}
    	log.info("addMovie() service end...");
        return movieRepository.save(movie);
    }
}
