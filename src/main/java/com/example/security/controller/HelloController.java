package com.example.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/admin")
    public Map<String, Object> adminEndpoint() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Admin Access Granted");
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("username", auth.getName());
        response.put("authorities", auth.getAuthorities());
        
        return response;
    }

    @GetMapping("/user")
    public Map<String, Object> userEndpoint() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User Access Granted");
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("username", auth.getName());
        response.put("authorities", auth.getAuthorities());
        
        return response;
    }

    @GetMapping("/public")
    public Map<String, Object> publicEndpoint() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Public Access - No Authentication Required");
        response.put("timestamp", LocalDateTime.now().toString());
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            response.put("authenticated_user", auth.getName());
        } else {
            response.put("authenticated_user", "none");
        }
        
        return response;
    }
} 