package com.example.aop.repository;

import com.example.aop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByActiveTrue();
    List<User> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
}
