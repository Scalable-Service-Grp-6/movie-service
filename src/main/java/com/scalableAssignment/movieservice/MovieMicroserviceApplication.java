package com.scalableAssignment.movieservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieMicroserviceApplication.class, args);
		System.out.println("✔ Started MovieMicroserviceApplication...");
	}

}
