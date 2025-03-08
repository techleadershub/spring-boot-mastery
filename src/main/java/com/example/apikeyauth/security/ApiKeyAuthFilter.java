package com.example.apikeyauth.security;

import com.example.apikeyauth.service.ApiKeyService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApiKeyAuthFilter extends OncePerRequestFilter {

    private final ApiKeyService apiKeyService;
    
    @Value("${api.key.header.name}")
    private String apiKeyHeaderName;
    
    @Value("${api.key.admin.value}")
    private String adminApiKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // Extract API key from header
        String apiKey = request.getHeader(apiKeyHeaderName);
        
        // If API key is present, validate it
        if (apiKey != null && !apiKey.isBlank()) {
            log.debug("API Key found in request: {}", maskApiKey(apiKey));
            
            // Check if it's the admin API key
            if (adminApiKey.equals(apiKey)) {
                // Set admin authentication
                SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(
                        "admin", null, 
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
                    )
                );
                log.debug("Admin API Key authentication successful");
            } 
            // Otherwise, check if it's a valid API key from the database
            else if (apiKeyService.isValidApiKey(apiKey)) {
                // Set user authentication
                SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(
                        "api-user", null, 
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_API_USER"))
                    )
                );
                log.debug("API Key authentication successful");
            } else {
                log.warn("Invalid API Key provided: {}", maskApiKey(apiKey));
            }
        }
        
        filterChain.doFilter(request, response);
    }
    
    private String maskApiKey(String apiKey) {
        if (apiKey.length() <= 8) {
            return "***";
        }
        return apiKey.substring(0, 4) + "..." + apiKey.substring(apiKey.length() - 4);
    }
} 