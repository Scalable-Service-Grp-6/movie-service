package com.scalableAssignment.movieservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scalableAssignment.movieservice.model.Theatre;
import com.scalableAssignment.movieservice.repository.TheatreRepository;

@Service
public class TheatreService {

	@Autowired
	private TheatreRepository theatreRepository;

	public Theatre addTheatre(Theatre theatre) {
	    return theatreRepository.save(theatre);
	}
	
	public List<Theatre> getAllTheatres() {
		return theatreRepository.findAll();
	}

}
