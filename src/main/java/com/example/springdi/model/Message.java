package com.example.springdi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Simple POJO for demonstration purposes
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String content;
    private String sender;
    private long timestamp;
    
    public Message(String content, String sender) {
        this.content = content;
        this.sender = sender;
        this.timestamp = System.currentTimeMillis();
    }
} 