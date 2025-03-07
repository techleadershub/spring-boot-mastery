package com.example.demo.controller;

import com.example.demo.model.BusinessData;
import com.example.demo.service.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BusinessController {

    private final BusinessService businessService;

    @GetMapping("/public")
    public ResponseEntity<Map<String, String>> publicEndpoint() {
        return ResponseEntity.ok(Map.of(
            "message", "Public endpoint - Hello, anonymous user!",
            "timestamp", java.time.LocalDateTime.now().toString()
        ));
    }

    @GetMapping("/user")
    public ResponseEntity<BusinessData> userEndpoint(Authentication authentication) {
        return ResponseEntity.ok(
            businessService.createData("User data", authentication.getName())
        );
    }

    @GetMapping("/admin")
    public ResponseEntity<List<BusinessData>> adminEndpoint() {
        return ResponseEntity.ok(businessService.getAllData());
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<BusinessData> getData(@PathVariable Long id) {
        return ResponseEntity.ok(businessService.getDataById(id));
    }

    @PostMapping("/data")
    public ResponseEntity<BusinessData> createData(
            @RequestBody Map<String, String> request,
            Authentication authentication) {
        return ResponseEntity.ok(
            businessService.createData(
                request.get("data"),
                authentication.getName()
            )
        );
    }

    @PutMapping("/data/{id}")
    public ResponseEntity<Void> updateData(
            @PathVariable Long id,
            @RequestBody Map<String, String> request) {
        businessService.updateData(id, request.get("data"));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/data/{id}")
    public ResponseEntity<Void> deleteData(@PathVariable Long id) {
        businessService.deleteData(id);
        return ResponseEntity.ok().build();
    }
} 