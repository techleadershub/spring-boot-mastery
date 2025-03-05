package com.example.springdi.beans;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class ScopeBean {
    private static final Logger logger = LoggerFactory.getLogger(ScopeBean.class);
    
    private final String id;
    private final LocalDateTime createdAt;
    private int counter;
    
    public ScopeBean() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.counter = 0;
        logger.info("Created new {} with ID: {}", this.getClass().getSimpleName(), id);
    }
    
    public int increment() {
        return ++counter;
    }
} 