package com.example.springdi;

import com.example.springdi.component.MyBean;
import com.example.springdi.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDiApplication {
    
    private static final Logger logger = LoggerFactory.getLogger(SpringDiApplication.class);
    
    public static void main(String[] args) {
        logger.info("Starting Spring DI Practice Application");
        
        // Create Spring context using Java configuration
        try (var context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            // Verify MyBean configuration
            logger.info("Checking MyBean configuration...");
            
            // Check if MyBean exists in context
            boolean containsMyBean = context.containsBean("myBean");
            logger.info("Contains MyBean: {}", containsMyBean);
            
            try {
                // Get and use MyBean
                MyBean myBean = context.getBean(MyBean.class);
                logger.info("Successfully retrieved MyBean: {}", myBean);
                myBean.doSomething();
            } catch (Exception e) {
                logger.error("Error accessing MyBean: {}", e.getMessage());
            }
            

        }
        
        // Demonstrate XML configuration (legacy approach)
        XmlConfigExample.demonstrateXmlConfig();
        
        logger.info("Spring DI Practice Application completed");
    }
} 