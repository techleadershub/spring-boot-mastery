package com.example.demo.service;

import com.example.demo.model.BusinessData;
import com.example.demo.repository.BusinessDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessService {

    private final BusinessDataRepository repository;

    @PreAuthorize("hasRole('ADMIN')")
    public List<BusinessData> getAllData() {
        return repository.findAll();
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public BusinessData createData(String data, String owner) {
        return repository.save(new BusinessData(data, owner));
    }

    @Secured("ROLE_USER")
    public void updateData(Long id, String newData) {
        BusinessData data = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Data not found"));
        data.setData(newData);
        repository.save(data);
    }

    @PostAuthorize("returnObject.owner == authentication.name")
    public BusinessData getDataById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Data not found"));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteData(Long id) {
        repository.deleteById(id);
    }
} 