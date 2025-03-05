package com.example.springdi.service;

import com.example.springdi.events.UserRegisteredEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class WhatsappService {
    @EventListener
    public void sendWhatsappNotification(UserRegisteredEvent event) {
        System.out.println("Sending WhatsApp notification to " + event.getUsername());
    }
}
