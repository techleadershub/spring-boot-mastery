package com.example.demo.service;

import com.example.demo.aspect.annotation.LogExecutionTime;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class ExampleService {

    public String simpleMethod(String input) {
        return "Hello, " + input + "!";
    }

    @LogExecutionTime
    public String timeConsumingMethod(String input, int delay) {
        try {
            // Simulate a time-consuming operation
            TimeUnit.MILLISECONDS.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Processed after " + delay + "ms: " + input;
    }

    @LogExecutionTime
    public String exceptionThrowingMethod(boolean shouldThrow) {
        if (shouldThrow) {
            throw new RuntimeException("Deliberate exception for demonstration");
        }
        return "No exception thrown";
    }
} 