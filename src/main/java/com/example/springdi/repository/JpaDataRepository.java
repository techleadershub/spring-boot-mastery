package com.example.springdi.repository;

import com.example.springdi.model.Message;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * JPA implementation of DataRepository
 * This class is annotated with @Repository to be discovered by component scanning
 * It also demonstrates lifecycle callbacks with @PostConstruct and @PreDestroy
 */
@Repository
public class JpaDataRepository implements DataRepository {
    
    private static final Logger logger = LoggerFactory.getLogger(JpaDataRepository.class);
    
    @PostConstruct
    public void init() {
        logger.info("Initializing JPA repository");
        // In a real application, this would set up JPA EntityManager, etc.
    }
    
    @PreDestroy
    public void cleanup() {
        logger.info("Cleaning up JPA repository resources");
        // In a real application, this would close connections, etc.
    }
    
    @Override
    public void save(Message message) {
        logger.info("JPA: Saving message from {}", message.getSender());
        // In a real application, this would use JPA EntityManager to persist
    }
    
    @Override
    public Optional<Message> findById(String id) {
        logger.info("JPA: Finding message by ID: {}", id);
        // In a real application, this would use JPA EntityManager to find
        return Optional.empty();
    }
    
    @Override
    public List<Message> findAll() {
        logger.info("JPA: Finding all messages");
        // In a real application, this would use JPA EntityManager to query
        return new ArrayList<>();
    }
    
    @Override
    public String getRepositoryName() {
        return "JPA Repository";
    }
} 