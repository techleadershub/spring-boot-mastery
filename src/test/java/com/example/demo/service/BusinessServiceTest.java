package com.example.demo.service;

import com.example.demo.model.BusinessData;
import com.example.demo.repository.BusinessDataRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BusinessServiceTest {

    @Autowired
    private BusinessService businessService;

    @Autowired
    private BusinessDataRepository repository;

    @Test
    @WithMockUser(roles = "ADMIN")
    void whenAdmin_thenCanAccessAllData() {
        assertDoesNotThrow(() -> businessService.getAllData());
    }

    @Test
    @WithMockUser(roles = "USER")
    void whenUser_thenCannotAccessAllData() {
        assertThrows(AccessDeniedException.class, () -> businessService.getAllData());
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void whenOwner_thenCanAccessOwnData() {
        BusinessData data = businessService.createData("test data", "testuser");
        assertDoesNotThrow(() -> businessService.getDataById(data.getId()));
    }

    @Test
    @WithMockUser(username = "otheruser", roles = "USER")
    void whenNotOwner_thenCannotAccessData() {
        BusinessData data = repository.save(new BusinessData("test data", "testuser"));
        assertThrows(AccessDeniedException.class, () -> businessService.getDataById(data.getId()));
    }

    @Test
    @WithMockUser(roles = {"USER", "ADMIN"})
    void whenUserOrAdmin_thenCanCreateData() {
        assertDoesNotThrow(() -> businessService.createData("test data", "testuser"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void whenAdmin_thenCanDeleteData() {
        BusinessData data = repository.save(new BusinessData("test data", "testuser"));
        assertDoesNotThrow(() -> businessService.deleteData(data.getId()));
    }

    @Test
    @WithMockUser(roles = "USER")
    void whenUser_thenCannotDeleteData() {
        BusinessData data = repository.save(new BusinessData("test data", "testuser"));
        assertThrows(AccessDeniedException.class, () -> businessService.deleteData(data.getId()));
    }
} 