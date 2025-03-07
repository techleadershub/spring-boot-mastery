package com.example.demo.repository;

import com.example.demo.model.BusinessData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessDataRepository extends JpaRepository<BusinessData, Long> {
} 