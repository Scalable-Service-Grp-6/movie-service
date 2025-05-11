package com.scalableAssignment.movieservice.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "movies")
@Data
public class Movie {
    @Id
    private String id;

    private String title;
    private String genre;
    private String description;
    private String director;
    private String language;
    private String releaseDate;
    private List<String> cast;
    private List<Theatre> theatres;    

}
