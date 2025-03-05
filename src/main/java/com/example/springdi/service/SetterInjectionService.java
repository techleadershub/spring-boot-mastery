package com.example.springdi.service;

import com.example.springdi.model.ExternalService;
import com.example.springdi.model.Message;
import com.example.springdi.repository.DataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Service implementation that demonstrates setter injection
 */
@Service
public class SetterInjectionService implements DemoService {
    
    private static final Logger logger = LoggerFactory.getLogger(SetterInjectionService.class);
    
    private DataRepository dataRepository;
    private ExternalService externalService;
    
    public SetterInjectionService() {
        logger.info("SetterInjectionService created with no dependencies (will be injected via setters)");
    }
    
    /**
     * Setter injection with @Autowired
     */
    @Autowired
    public void setDataRepository(@Qualifier("secondaryDataRepository") DataRepository dataRepository) {
        this.dataRepository = dataRepository;
        logger.info("Setter injected repository: {}", dataRepository.getRepositoryName());
    }
    
    /**
     * Another setter injection
     */
    @Autowired
    public void setExternalService(ExternalService externalService) {
        this.externalService = externalService;
        logger.info("Setter injected external service: {}", externalService);
    }
    
    @Override
    public void performDemo() {
        logger.info("Performing demo with Setter Injection");
        
        // Use injected repository
        Message message = new Message("Hello from setter injection", "SetterService");
        dataRepository.save(message);
        
        // Use injected external service
        String externalData = externalService.fetchData();
        logger.info("External service returned: {}", externalData);
    }
} 