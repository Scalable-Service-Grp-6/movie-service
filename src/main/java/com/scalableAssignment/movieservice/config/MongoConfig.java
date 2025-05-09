package com.scalableAssignment.movieservice.config;
 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;
 
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
 
@Configuration
@EnableMongoRepositories(basePackages = "com.scalableAssignment.movieservice.repository")
public class MongoConfig {
 
    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;
    @Value("${spring.data.mongodb.database}")
    private String database;
 
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(mongoUri);
    }
 
    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), database);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}