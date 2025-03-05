package com.example.springdi.service;

import com.example.springdi.events.UserRegisteredEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final ApplicationEventPublisher eventPublisher;

    public UserService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void registerUser(String username, String email) {
        System.out.println("User registered: " + username);

        // Publish event
        UserRegisteredEvent event = new UserRegisteredEvent(this, username, email);
        eventPublisher.publishEvent(event);
    }
}

