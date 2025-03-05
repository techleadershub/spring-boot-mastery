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
 * Service implementation that demonstrates field injection
 * Note: Field injection is not recommended for production code as it makes testing harder
 * and hides dependencies. It's shown here for educational purposes only.
 */
@Service
public class FieldInjectionService implements DemoService {
    
    private static final Logger logger = LoggerFactory.getLogger(FieldInjectionService.class);
    
    /**
     * Field injection with @Autowired
     * This is not recommended for production code
     */
    @Autowired
    @Qualifier("jpaDataRepository")
    private DataRepository dataRepository;
    
    /**
     * Another field injection
     */
    @Autowired
    private ExternalService externalService;
    
    public FieldInjectionService() {
        logger.info("FieldInjectionService created with no dependencies (will be injected via fields)");
    }
    
    @Override
    public void performDemo() {
        logger.info("Performing demo with Field Injection");
        
        // Log injected dependencies
        logger.info("Field injected repository: {}", dataRepository.getRepositoryName());
        logger.info("Field injected external service: {}", externalService);
        
        // Use injected repository
        Message message = new Message("Hello from field injection", "FieldService");
        dataRepository.save(message);
        
        // Use injected external service
        String externalData = externalService.fetchData();
        logger.info("External service returned: {}", externalData);
    }
} 