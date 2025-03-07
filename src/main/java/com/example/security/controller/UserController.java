package com.example.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/public")
    public ResponseEntity<?> publicEndpoint() {
        return ResponseEntity.ok().body("Public endpoint - accessible by anyone");
    }

    @GetMapping("/user")
    public ResponseEntity<?> userEndpoint(Authentication authentication) {
        return ResponseEntity.ok().body("User endpoint - Hello, " + authentication.getName());
    }
} 