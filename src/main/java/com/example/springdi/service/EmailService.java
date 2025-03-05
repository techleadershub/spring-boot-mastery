package com.example.springdi.service;


import com.example.springdi.events.UserRegisteredEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @EventListener
    public void sendWelcomeEmail(UserRegisteredEvent event) {
        System.out.println("Sending welcome email to " + event.getEmail());
    }
}
