package com.example.demo.service;

import com.example.demo.aspect.annotation.LogExecutionTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CachingExampleService {
    
    private static final Logger logger = LoggerFactory.getLogger(CachingExampleService.class);
    
    /**
     * A method that simulates an expensive computation.
     * This method will be cached by our caching aspect.
     */
    @LogExecutionTime
    public long fibonacci(int n) {
        logger.info("Computing fibonacci({})...", n);
        
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        
        // Simulate expensive computation
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
} 