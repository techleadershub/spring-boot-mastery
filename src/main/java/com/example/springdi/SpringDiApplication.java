package com.example.springdi;

import com.example.springdi.component.HeavyComponent;
import com.example.springdi.component.MyBean;
import com.example.springdi.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDiApplication {

    private static final Logger logger = LoggerFactory.getLogger(SpringDiApplication.class);

    public static void main(String[] args) {
        logger.info("Starting Spring DI Practice Application");

        // Load Spring Context
        try (var context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            logger.info("Spring Context initialized");


            //Instantiate MyBean
            logger.info("Requesting MyBean bean...");
            var myBean = context.getBean("myBean", MyBean.class);
            myBean.doSomething();


            // HeavyComponent has not been initialized yet because of @Lazy
            logger.info("Requesting HeavyComponent bean...");
            HeavyComponent heavyComponent = context.getBean(HeavyComponent.class); // This triggers initialization
            heavyComponent.doSomething();
        }

        logger.info("Spring DI Practice Application completed");
    }
}
