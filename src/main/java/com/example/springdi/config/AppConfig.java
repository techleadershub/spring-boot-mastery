package com.example.springdi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.example.springdi.component.HeavyComponent;
import com.example.springdi.component.MyBean;

@Configuration
public class AppConfig {

    @Bean
    MyBean myBean() {
        return new MyBean();
    }

    @Lazy
    @Bean
    HeavyComponent heavyComponent() {
        return new HeavyComponent();
    }
    
}
