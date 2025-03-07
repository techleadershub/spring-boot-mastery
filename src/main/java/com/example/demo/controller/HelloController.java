package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/public")
    public ResponseEntity<Map<String, String>> publicEndpoint() {
        return ResponseEntity.ok(Map.of(
            "message", "Hello from public endpoint!",
            "timestamp", java.time.LocalDateTime.now().toString()
        ));
    }

    @GetMapping("/private")
    public ResponseEntity<Map<String, String>> privateEndpoint() {
        return ResponseEntity.ok(Map.of(
            "message", "Hello from private endpoint!",
            "timestamp", java.time.LocalDateTime.now().toString()
        ));
    }
} 