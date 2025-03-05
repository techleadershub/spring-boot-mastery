package com.example.springdi.service;

import com.example.springdi.model.ExternalService;
import com.example.springdi.model.Message;
import com.example.springdi.repository.DataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Service implementation that demonstrates constructor injection (recommended approach)
 */
@Service
@Primary
public class ConstructorInjectionService implements DemoService {
    
    private static final Logger logger = LoggerFactory.getLogger(ConstructorInjectionService.class);
    
    private final DataRepository primaryRepository;
    private final DataRepository secondaryRepository;
    private final ExternalService externalService;
    
    /**
     * Constructor injection with @Qualifier to specify which bean to inject
     * when multiple beans of the same type exist
     */
    public ConstructorInjectionService(
            DataRepository primaryRepository,
            @Qualifier("secondaryDataRepository") DataRepository secondaryRepository,
            ExternalService externalService) {
        this.primaryRepository = primaryRepository;
        this.secondaryRepository = secondaryRepository;
        this.externalService = externalService;
        
        logger.info("ConstructorInjectionService created with:");
        logger.info("- Primary repository: {}", primaryRepository.getRepositoryName());
        logger.info("- Secondary repository: {}", secondaryRepository.getRepositoryName());
        logger.info("- External service: {}", externalService);
    }
    
    @Override
    public void performDemo() {
        logger.info("Performing demo with Constructor Injection");
        
        // Use primary repository
        Message message1 = new Message("Hello from constructor injection", "ConstructorService");
        primaryRepository.save(message1);
        
        // Use secondary repository
        Message message2 = new Message("Using qualified repository", "ConstructorService");
        secondaryRepository.save(message2);
        
        // Use external service
        String externalData = externalService.fetchData();
        logger.info("External service returned: {}", externalData);
    }
} 