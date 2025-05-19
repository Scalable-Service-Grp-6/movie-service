package com.scalableAssignment.movieservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scalableAssignment.movieservice.model.Theatre;
import com.scalableAssignment.movieservice.repository.TheatreRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TheatreService {

	@Autowired
	private TheatreRepository theatreRepository;

	public Theatre addTheatre(Theatre theatre) {
		log.info("addTheatre() start...");
		log.info("Theatre added successfully...");
	    return theatreRepository.save(theatre);
	}
	
	public List<Theatre> getAllTheatres() {
		log.info("getAllTheatres() start...");
		log.info("Fetching Theatrers...");
		return theatreRepository.findAll();
	}

}
