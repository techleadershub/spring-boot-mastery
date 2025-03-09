package com.example.aop.service;

import com.example.aop.annotation.LogExecutionTime;
import com.example.aop.annotation.LogMethod;
import com.example.aop.model.User;
import com.example.aop.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * Find all users
     */
    @LogMethod("Get all users from database")
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Find user by ID
     */
    @LogMethod
    @LogExecutionTime
    @Transactional(readOnly = true)
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    /**
     * Find active users
     */
    @LogMethod("Get only active users")
    @Transactional(readOnly = true)
    public List<User> findActiveUsers() {
        return userRepository.findByActiveTrue();
    }

    /**
     * Search users by name
     */
    @LogMethod
    @Transactional(readOnly = true)
    public List<User> searchUsersByName(String searchTerm) {
        return userRepository.findByFirstNameContainingOrLastNameContaining(searchTerm, searchTerm);
    }

    /**
     * Create a new user
     */
    @LogMethod("Creating new user")
    @LogExecutionTime
    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Update an existing user
     */
    @LogMethod("Updating user details")
    @LogExecutionTime
    @Transactional
    public User updateUser(Long id, User userDetails) {
        User user = findUserById(id);
        
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setPhone(userDetails.getPhone());
        user.setAddress(userDetails.getAddress());
        user.setActive(userDetails.isActive());
        
        return userRepository.save(user);
    }

    /**
     * Delete a user
     */
    @LogMethod("Deleting user")
    @Transactional
    public void deleteUser(Long id) {
        User user = findUserById(id);
        userRepository.delete(user);
    }

    /**
     * Update user status
     */
    @LogMethod
    @Transactional
    public User updateUserStatus(Long id, boolean active) {
        User user = findUserById(id);
        user.setActive(active);
        return userRepository.save(user);
    }
}
