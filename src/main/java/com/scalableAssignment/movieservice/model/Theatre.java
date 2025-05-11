package com.scalableAssignment.movieservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "theatre")
@Data
public class Theatre {

	@Id
    private String id;
	
	private String theatreName;
	private String showTime;
	private String showDate;

}
