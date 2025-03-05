package com.example.springdi.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.springdi.repository.MyRepository;

@Configuration
public class RepositoryConfig {

    @Bean
    public MyRepository myRepository() {
        return new MyRepository();
    }
}
