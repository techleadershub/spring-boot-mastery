package com.example.aop.config;

import com.example.aop.model.User;
import com.example.aop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Component to initialize test data on application startup
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        log.info("Initializing test data...");
        
        // Create test users if the database is empty
        if (userRepository.count() == 0) {
            List<User> users = Arrays.asList(
                User.builder()
                    .firstName("John")
                    .lastName("Doe")
                    .email("john.doe@example.com")
                    .phone("555-123-4567")
                    .address("123 Main St, Anytown, USA")
                    .active(true)
                    .build(),
                    
                User.builder()
                    .firstName("Jane")
                    .lastName("Smith")
                    .email("jane.smith@example.com")
                    .phone("555-987-6543")
                    .address("456 Oak Ave, Somewhere, USA")
                    .active(true)
                    .build(),
                    
                User.builder()
                    .firstName("Michael")
                    .lastName("Johnson")
                    .email("michael.johnson@example.com")
                    .phone("555-555-5555")
                    .address("789 Pine Rd, Nowhere, USA")
                    .active(false)
                    .build(),
                    
                User.builder()
                    .firstName("Emily")
                    .lastName("Brown")
                    .email("emily.brown@example.com")
                    .phone("555-111-2222")
                    .address("321 Elm St, Anywhere, USA")
                    .active(true)
                    .build(),
                    
                User.builder()
                    .firstName("David")
                    .lastName("Wilson")
                    .email("david.wilson@example.com")
                    .phone("555-333-4444")
                    .address("654 Maple Dr, Somewhere, USA")
                    .active(false)
                    .build()
            );
            
            userRepository.saveAll(users);
            log.info("Saved {} test users to the database", users.size());
        }
    }
}
