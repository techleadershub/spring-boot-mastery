package com.example.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("roles", authentication.getAuthorities());
        return "home";
    }

    @GetMapping("/public")
    @ResponseBody
    public Map<String, String> publicEndpoint() {
        return Map.of("message", "Public endpoint - Hello!");
    }

    @GetMapping("/user")
    @ResponseBody
    public Map<String, String> userEndpoint(Authentication authentication) {
        return Map.of(
            "message", "User endpoint - Hello, " + authentication.getName() + "!",
            "roles", authentication.getAuthorities().toString()
        );
    }

    @GetMapping("/admin")
    @ResponseBody
    public Map<String, String> adminEndpoint(Authentication authentication) {
        return Map.of(
            "message", "Admin endpoint - Hello, " + authentication.getName() + "!",
            "roles", authentication.getAuthorities().toString()
        );
    }

    @GetMapping("/api/public")
    @ResponseBody
    public Map<String, String> apiPublicEndpoint() {
        return Map.of("message", "API Public endpoint - Hello!");
    }

    @GetMapping("/api/secure")
    @ResponseBody
    public Map<String, String> apiSecureEndpoint(Authentication authentication) {
        return Map.of(
            "message", "API Secure endpoint - Hello, " + authentication.getName() + "!",
            "roles", authentication.getAuthorities().toString()
        );
    }
} 