package com.example.springdi.model;

import lombok.Getter;
import lombok.ToString;

/**
 * Example of a class that is not managed by Spring but created manually
 * and provided as a bean through @Bean method in configuration.
 */
@Getter
@ToString
public class ExternalService {
    private final String endpoint;
    private final int timeout;
    
    public ExternalService(String endpoint, int timeout) {
        this.endpoint = endpoint;
        this.timeout = timeout;
    }
    
    public String fetchData() {
        // Simulate fetching data from external service
        return "Data from " + endpoint;
    }
} 