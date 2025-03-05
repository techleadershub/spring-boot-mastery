package com.example.springdi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ServiceConfig.class, RepositoryConfig.class})  // Importing modular configurations
public class MainConfig {
}
