package com.example.security.config;

public class SecurityConstants {
    public static final String JWT_COOKIE_NAME = "jwt-token";
    public static final String[] PUBLIC_PATHS = {
        "/auth/**",
        "/api/public/**",
        "/h2-console/**"
    };
    public static final String[] ADMIN_PATHS = {
        "/api/admin/**"
    };
    
    private SecurityConstants() {
        // Private constructor to prevent instantiation
    }
} 