package com.example.springdi.service;

import com.example.springdi.events.UserRegisteredEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsService {
    @EventListener
    public void trackUserRegistration(UserRegisteredEvent event) {
        System.out.println("Tracking new user " + event.getUsername());
    }
}
