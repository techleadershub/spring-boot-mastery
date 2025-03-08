package com.example.security.config;

import com.example.security.entity.User;
import com.example.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Only add default users if the repository is empty
        if (userRepository.count() == 0) {
            log.info("Initializing default users");
            
            // Create admin user
            User admin = new User(
                "admin",
                "admin@example.com",
                passwordEncoder.encode("admin123")
            );
            admin.setRoles(Set.of("ADMIN", "USER"));
            userRepository.save(admin);
            log.info("Created admin user: {}", admin.getUsername());
            
            // Create regular user
            User user = new User(
                "user",
                "user@example.com",
                passwordEncoder.encode("user123")
            );
            user.setRoles(Set.of("USER"));
            userRepository.save(user);
            log.info("Created regular user: {}", user.getUsername());
        }
    }
} 