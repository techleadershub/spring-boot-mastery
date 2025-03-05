package com.example.springdi;

import com.example.springdi.config.MainConfig;
import com.example.springdi.repository.MyRepository;
import com.example.springdi.service.MyService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDiApplication {

    private static final Logger logger = LoggerFactory.getLogger(SpringDiApplication.class);

    public static void main(String[] args) {
        logger.info("Starting Spring DI Practice Application");

        // Load Spring Context
        try (var context = new AnnotationConfigApplicationContext(MainConfig.class)) {
            logger.info("Spring Context initialized");

            // Get MyService bean
            var myService = context.getBean(MyService.class);
            myService.doSomething();

            // Get MyRepository bean 
            var myRepository = context.getBean(MyRepository.class);
            myRepository.doQuery();
        }

        logger.info("Spring DI Practice Application completed");
    }
}
