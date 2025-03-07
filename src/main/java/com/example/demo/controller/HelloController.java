package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
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
            "message", "Public endpoint - Hello, anonymous user!",
            "timestamp", java.time.LocalDateTime.now().toString()
        ));
    }

    @GetMapping("/user")
    public ResponseEntity<Map<String, Object>> userEndpoint(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(Map.of(
            "message", "User endpoint - Hello, " + jwt.getSubject() + "!",
            "token_details", Map.of(
                "subject", jwt.getSubject(),
                "issued_at", jwt.getIssuedAt().toString(),
                "expires_at", jwt.getExpiresAt().toString(),
                "scopes", jwt.getClaimAsString("scope")
            )
        ));
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> adminEndpoint(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(Map.of(
            "message", "Admin endpoint - Hello, " + jwt.getSubject() + "!",
            "token_details", Map.of(
                "subject", jwt.getSubject(),
                "issued_at", jwt.getIssuedAt().toString(),
                "expires_at", jwt.getExpiresAt().toString(),
                "scopes", jwt.getClaimAsString("scope")
            )
        ));
    }
} 