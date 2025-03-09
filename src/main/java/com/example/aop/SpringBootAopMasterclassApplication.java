package com.example.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringBootAopMasterclassApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAopMasterclassApplication.class, args);
    }
}
