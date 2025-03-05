package com.example.springdi.controller;

import com.example.springdi.beans.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/scopes")
@RequiredArgsConstructor
public class ScopeDemoController {

    private final SingletonBean singletonBean;
    private final ApplicationContext context;
    private final RequestBean requestBean;
    private final SessionBean sessionBean;
    private final ApplicationBean applicationBean;

    @GetMapping("/demo")
    public Map<String, Object> demonstrateScopes() {
        Map<String, Object> response = new HashMap<>();

        // Demonstrate Singleton scope
        response.put("singleton", Map.of(
            "id", singletonBean.getId(),
            "createdAt", singletonBean.getCreatedAt(),
            "counter", singletonBean.increment()
        ));

        // Demonstrate Prototype scope
        PrototypeBean prototype1 = context.getBean(PrototypeBean.class);
        PrototypeBean prototype2 = context.getBean(PrototypeBean.class);
        prototype1.increment();
        prototype2.increment();
        
        response.put("prototype1", Map.of(
            "id", prototype1.getId(),
            "createdAt", prototype1.getCreatedAt(),
            "counter", prototype1.getCounter()
        ));
        
        response.put("prototype2", Map.of(
            "id", prototype2.getId(),
            "createdAt", prototype2.getCreatedAt(),
            "counter", prototype2.getCounter()
        ));

        // Demonstrate Request scope
        response.put("request", Map.of(
            "id", requestBean.getId(),
            "createdAt", requestBean.getCreatedAt(),
            "counter", requestBean.increment()
        ));

        // Demonstrate Session scope
        response.put("session", Map.of(
            "id", sessionBean.getId(),
            "createdAt", sessionBean.getCreatedAt(),
            "counter", sessionBean.increment()
        ));

        // Demonstrate Application scope
        response.put("application", Map.of(
            "id", applicationBean.getId(),
            "createdAt", applicationBean.getCreatedAt(),
            "counter", applicationBean.increment()
        ));

        return response;
    }

    @GetMapping("/singleton")
    public Map<String, Object> getSingleton() {
        return Map.of(
            "id", singletonBean.getId(),
            "createdAt", singletonBean.getCreatedAt(),
            "counter", singletonBean.increment()
        );
    }

    @GetMapping("/request")
    public Map<String, Object> getRequest() {
        return Map.of(
            "id", requestBean.getId(),
            "createdAt", requestBean.getCreatedAt(),
            "counter", requestBean.increment()
        );
    }

    @GetMapping("/session")
    public Map<String, Object> getSession() {
        return Map.of(
            "id", sessionBean.getId(),
            "createdAt", sessionBean.getCreatedAt(),
            "counter", sessionBean.increment()
        );
    }

    @GetMapping("/application")
    public Map<String, Object> getApplication() {
        return Map.of(
            "id", applicationBean.getId(),
            "createdAt", applicationBean.getCreatedAt(),
            "counter", applicationBean.increment()
        );
    }
} 