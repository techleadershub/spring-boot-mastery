//sample MyBean Class
package com.example.springdi.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class MyBean {
    
    private static final Logger logger = LoggerFactory.getLogger(MyBean.class);
    
    public MyBean() {
        logger.info("MyBean constructor called");
    }
    
    @PostConstruct
    public void init() {
        logger.info("MyBean initialized via @PostConstruct");
    }
    
    public void doSomething() {
        logger.info("MyBean is doing something!");
    }
}