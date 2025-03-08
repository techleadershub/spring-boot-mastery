package com.example.apikeyauth.service;

import com.example.apikeyauth.model.ApiKey;
import com.example.apikeyauth.repository.ApiKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApiKeyService {

    private final ApiKeyRepository apiKeyRepository;
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    public List<ApiKey> getAllApiKeys() {
        return apiKeyRepository.findAll();
    }

    public Optional<ApiKey> getApiKeyByValue(String keyValue) {
        return apiKeyRepository.findByKeyValue(keyValue);
    }

    public boolean isValidApiKey(String keyValue) {
        Optional<ApiKey> apiKeyOpt = getApiKeyByValue(keyValue);
        if (apiKeyOpt.isPresent()) {
            ApiKey apiKey = apiKeyOpt.get();
            boolean isValid = apiKey.isValid();
            if (isValid) {
                // Update last used timestamp
                apiKeyRepository.updateLastUsedAt(keyValue, LocalDateTime.now());
            }
            return isValid;
        }
        return false;
    }

    @Transactional
    public ApiKey generateApiKey(String name, Integer validityInDays) {
        String keyValue = generateRandomKey();
        
        ApiKey apiKey = new ApiKey();
        apiKey.setKeyValue(keyValue);
        apiKey.setName(name);
        apiKey.setEnabled(true);
        apiKey.setCreatedAt(LocalDateTime.now());
        
        if (validityInDays != null) {
            apiKey.setExpiresAt(LocalDateTime.now().plus(validityInDays, ChronoUnit.DAYS));
        }
        
        return apiKeyRepository.save(apiKey);
    }

    @Transactional
    public void revokeApiKey(String keyValue) {
        apiKeyRepository.revokeApiKey(keyValue);
    }

    @Transactional
    public void updateApiKeyExpiration(String keyValue, Integer validityInDays) {
        LocalDateTime expiresAt = validityInDays == null ? null : 
                                  LocalDateTime.now().plus(validityInDays, ChronoUnit.DAYS);
        apiKeyRepository.updateExpiresAt(keyValue, expiresAt);
    }

    private String generateRandomKey() {
        byte[] randomBytes = new byte[32];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
} 