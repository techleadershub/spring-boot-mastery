package com.example.springdi.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.springdi.service.MyService;

@Configuration
public class ServiceConfig {

    @Bean
    public MyService myService() {
        return new MyService();
    }
}
