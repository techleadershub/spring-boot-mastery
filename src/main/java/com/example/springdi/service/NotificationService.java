package com.example.springdi.service;

import com.example.springdi.events.UserRegisteredEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @EventListener
    public void sendSmsNotification(UserRegisteredEvent event) {
        System.out.println("Sending SMS notification to " + event.getUsername());
    }
}
