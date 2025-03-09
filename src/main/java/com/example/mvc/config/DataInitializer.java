package com.example.mvc.config;

import com.example.mvc.model.User;
import com.example.mvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    
    @Autowired
    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public void run(String... args) throws Exception {
        // Create sample users if the database is empty
        long count = userRepository.count();
        
        if (count == 0) {
            System.out.println("Initializing test users...");
            
            userRepository.save(new User(null, "John Doe", "john.doe@example.com", null));
            userRepository.save(new User(null, "Jane Smith", "jane.smith@example.com", null));
            userRepository.save(new User(null, "Bob Johnson", "bob.johnson@example.com", null));
            
            System.out.println("Test users created successfully!");
        }
    }
}
