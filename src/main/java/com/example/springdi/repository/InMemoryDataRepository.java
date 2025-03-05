package com.example.springdi.repository;

import com.example.springdi.model.Message;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * In-memory implementation of DataRepository
 * This class is not annotated with @Repository because instances
 * are created manually in the configuration class
 */
public class InMemoryDataRepository implements DataRepository {
    
    private static final Logger logger = LoggerFactory.getLogger(InMemoryDataRepository.class);
    
    private final Map<String, Message> dataStore = new HashMap<>();
    
    @Getter
    private final String repositoryName;
    
    public InMemoryDataRepository(String repositoryName) {
        this.repositoryName = repositoryName;
        logger.info("Created repository: {}", repositoryName);
    }
    
    @Override
    public void save(Message message) {
        String id = UUID.randomUUID().toString();
        dataStore.put(id, message);
        logger.info("Saved message with ID: {} in repository: {}", id, repositoryName);
    }
    
    @Override
    public Optional<Message> findById(String id) {
        return Optional.ofNullable(dataStore.get(id));
    }
    
    @Override
    public List<Message> findAll() {
        return new ArrayList<>(dataStore.values());
    }
} 