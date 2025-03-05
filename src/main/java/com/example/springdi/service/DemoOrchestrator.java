package com.example.springdi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service that demonstrates injection of collections and orchestrates all demo services
 */
@Service
public class DemoOrchestrator implements DemoService {
    
    private static final Logger logger = LoggerFactory.getLogger(DemoOrchestrator.class);
    
    private final List<DemoService> allDemoServices;
    private final DemoService primaryDemoService;
    
    /**
     * Constructor injection with a collection of all DemoService implementations
     * and a specific one identified by @Qualifier
     */
    public DemoOrchestrator(
            List<DemoService> allDemoServices,
            @Qualifier("constructorInjectionService") DemoService primaryDemoService) {
        
        // Remove this orchestrator from the list to avoid circular calls
        this.allDemoServices = allDemoServices.stream()
                .filter(service -> !(service instanceof DemoOrchestrator))
                .toList();
        
        this.primaryDemoService = primaryDemoService;
        
        logger.info("DemoOrchestrator created with {} demo services", this.allDemoServices.size());
    }
    
    @Override
    public void performDemo() {
        logger.info("=== Starting Demo Orchestration ===");
        
        // First run the primary service
        logger.info("--- Running Primary Demo Service ---");
        primaryDemoService.performDemo();
        
        // Then run all other services
        logger.info("--- Running All Other Demo Services ---");
        allDemoServices.stream()
                .filter(service -> service != primaryDemoService)
                .forEach(service -> {
                    logger.info("Running demo service: {}", service.getClass().getSimpleName());
                    service.performDemo();
                });
        
        logger.info("=== Demo Orchestration Completed ===");
    }
} 