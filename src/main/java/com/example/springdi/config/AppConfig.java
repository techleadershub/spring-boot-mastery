package com.example.springdi.config;

import com.example.springdi.component.MyBean;
import com.example.springdi.model.ExternalService;
import com.example.springdi.repository.DataRepository;
import com.example.springdi.repository.InMemoryDataRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan(basePackages = {
    "com.example.springdi.component",
    "com.example.springdi.service",
    "com.example.springdi.repository"
})
public class AppConfig {

    // Example of @Bean method for manual bean definition
    @Bean
    public ExternalService externalService() {
        return new ExternalService("https://api.example.com", 30);
    }
    
    // Example of multiple implementations with @Primary
    @Bean
    @Primary
    public DataRepository primaryDataRepository() {
        return new InMemoryDataRepository("Primary Repository");
    }
    
    @Bean
    public DataRepository secondaryDataRepository() {
        return new InMemoryDataRepository("Secondary Repository");
    }
    
    // Example of profile-specific beans
    @Bean
    @Profile("dev")
    public DataRepository devDataRepository() {
        return new InMemoryDataRepository("Development Repository");
    }
    
    @Bean
    @Profile("prod")
    public DataRepository prodDataRepository() {
        return new InMemoryDataRepository("Production Repository");
    }
} 