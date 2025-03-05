package com.example.springdi.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// This bean will NOT be initialized at startup
public class HeavyComponent {

    private static final Logger logger = LoggerFactory.getLogger(HeavyComponent.class);

    public HeavyComponent() {
        logger.info("HeavyComponent constructor called");
    }

    public void doSomething() {
        logger.info("HeavyComponent is doing something!");
    }
}
