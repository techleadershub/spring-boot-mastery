package com.example.springdi.config;

import com.example.springdi.component.MyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan(basePackages = "com.example.springdi")
public class AppConfig {

    @Bean
    public MyBean myBean() {
        return new MyBean();
    }
} 