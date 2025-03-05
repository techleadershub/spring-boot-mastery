package com.example.springdi;

import com.example.springdi.model.ExternalService;
import com.example.springdi.repository.DataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Example of using XML-based configuration (legacy approach)
 * This class is not used in the main application but is included for educational purposes
 */
public class XmlConfigExample {
    
    private static final Logger logger = LoggerFactory.getLogger(XmlConfigExample.class);
    
    public static void demonstrateXmlConfig() {
        logger.info("Demonstrating XML-based configuration");
        
        // Create Spring context using XML configuration
        try (var context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            
            // Get beans from XML context
            ExternalService xmlExternalService = context.getBean("xmlExternalService", ExternalService.class);
            DataRepository xmlDataRepository = context.getBean("xmlDataRepository", DataRepository.class);
            
            // Use the beans
            logger.info("XML configured external service: {}", xmlExternalService);
            logger.info("XML configured repository: {}", xmlDataRepository.getRepositoryName());
            
            // Log all beans in XML context
            logger.info("Beans loaded in XML application context:");
            String[] beanNames = context.getBeanDefinitionNames();
            for (String beanName : beanNames) {
                logger.info("Bean: {}", beanName);
            }

            
        }
        
        logger.info("XML configuration demonstration completed");
    }
} 