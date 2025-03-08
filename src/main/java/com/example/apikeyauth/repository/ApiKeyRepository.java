package com.example.apikeyauth.repository;

import com.example.apikeyauth.model.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {
    
    Optional<ApiKey> findByKeyValue(String keyValue);
    
    boolean existsByKeyValue(String keyValue);
    
    @Transactional
    @Modifying
    @Query("UPDATE ApiKey a SET a.lastUsedAt = ?2 WHERE a.keyValue = ?1")
    void updateLastUsedAt(String keyValue, LocalDateTime lastUsedAt);
    
    @Transactional
    @Modifying
    @Query("UPDATE ApiKey a SET a.enabled = false WHERE a.keyValue = ?1")
    void revokeApiKey(String keyValue);
    
    @Transactional
    @Modifying
    @Query("UPDATE ApiKey a SET a.expiresAt = ?2 WHERE a.keyValue = ?1")
    void updateExpiresAt(String keyValue, LocalDateTime expiresAt);
} 