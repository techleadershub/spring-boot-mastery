package com.example.springdi.repository;

import com.example.springdi.model.Message;

import java.util.List;
import java.util.Optional;

/**
 * Interface for data access operations
 */
public interface DataRepository {
    
    void save(Message message);
    
    Optional<Message> findById(String id);
    
    List<Message> findAll();
    
    String getRepositoryName();
} 