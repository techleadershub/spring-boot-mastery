package com.example.springdi;


import com.example.springdi.config.AppConfig;
import com.example.springdi.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EventDrivenApp {
    public static void main(String[] args) {
        // Initialize Spring context
        var context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Get UserService and trigger event
        UserService userService = context.getBean(UserService.class);
        userService.registerUser("john_doe", "john@example.com");

        // Close context
        context.close();
    }
}
