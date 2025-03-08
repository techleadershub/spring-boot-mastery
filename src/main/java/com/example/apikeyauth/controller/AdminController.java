package com.example.apikeyauth.controller;

import com.example.apikeyauth.dto.ApiKeyRequest;
import com.example.apikeyauth.dto.ApiKeyResponse;
import com.example.apikeyauth.model.ApiKey;
import com.example.apikeyauth.service.ApiKeyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ApiKeyService apiKeyService;

    @GetMapping("/api-keys")
    public ResponseEntity<List<ApiKeyResponse>> getAllApiKeys() {
        List<ApiKeyResponse> apiKeys = apiKeyService.getAllApiKeys().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(apiKeys);
    }

    @PostMapping("/generate-key")
    public ResponseEntity<ApiKeyResponse> generateApiKey(@Valid @RequestBody ApiKeyRequest request) {
        ApiKey apiKey = apiKeyService.generateApiKey(request.getName(), request.getValidityInDays());
        return new ResponseEntity<>(mapToResponse(apiKey), HttpStatus.CREATED);
    }

    @PutMapping("/api-keys/{keyValue}/revoke")
    public ResponseEntity<Map<String, String>> revokeApiKey(@PathVariable String keyValue) {
        apiKeyService.revokeApiKey(keyValue);
        Map<String, String> response = new HashMap<>();
        response.put("message", "API key revoked successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/api-keys/{keyValue}/expiration")
    public ResponseEntity<Map<String, String>> updateApiKeyExpiration(
            @PathVariable String keyValue,
            @RequestParam Integer validityInDays) {
        apiKeyService.updateApiKeyExpiration(keyValue, validityInDays);
        Map<String, String> response = new HashMap<>();
        response.put("message", "API key expiration updated successfully");
        return ResponseEntity.ok(response);
    }

    private ApiKeyResponse mapToResponse(ApiKey apiKey) {
        return new ApiKeyResponse(
                apiKey.getId(),
                apiKey.getKeyValue(),
                apiKey.getName(),
                apiKey.isEnabled(),
                apiKey.getCreatedAt(),
                apiKey.getExpiresAt(),
                apiKey.getLastUsedAt()
        );
    }
} 