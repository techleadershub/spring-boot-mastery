package com.example.demo.controller;

import com.example.demo.aspect.annotation.LogExecutionTime;
import com.example.demo.service.CachingExampleService;
import com.example.demo.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ExampleController {

    private final ExampleService exampleService;
    private final CachingExampleService cachingExampleService;

    @Autowired
    public ExampleController(ExampleService exampleService, CachingExampleService cachingExampleService) {
        this.exampleService = exampleService;
        this.cachingExampleService = cachingExampleService;
    }

    @GetMapping("/simple")
    public String simpleMethod(@RequestParam(defaultValue = "World") String name) {
        return exampleService.simpleMethod(name);
    }

    @GetMapping("/time-consuming")
    @LogExecutionTime
    public String timeConsumingMethod(
            @RequestParam(defaultValue = "Task") String task,
            @RequestParam(defaultValue = "1000") int delay) {
        return exampleService.timeConsumingMethod(task, delay);
    }

    @GetMapping("/exception")
    public String exceptionMethod(@RequestParam(defaultValue = "false") boolean shouldThrow) {
        return exampleService.exceptionThrowingMethod(shouldThrow);
    }
    
    @GetMapping("/fibonacci/{n}")
    public String fibonacci(@PathVariable int n) {
        long startTime = System.currentTimeMillis();
        long result = cachingExampleService.fibonacci(n);
        long endTime = System.currentTimeMillis();
        
        return String.format("Fibonacci(%d) = %d (calculated in %d ms)", 
                n, result, (endTime - startTime));
    }
} 