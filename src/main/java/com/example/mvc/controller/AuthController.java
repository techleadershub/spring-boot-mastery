package com.example.mvc.controller;

import com.example.mvc.model.User;
import com.example.mvc.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "auth/login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email, 
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {
        
        User user = userService.findByEmail(email);
        
        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "Invalid email address");
            return "redirect:/login";
        }
        
        // In a real application, you would check password too
        // For simplicity, we're just checking email
        
        // Store user in session
        session.setAttribute("currentUser", user);
        redirectAttributes.addFlashAttribute("message", "Login successful");
        
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        // Clear the session
        session.invalidate();
        
        redirectAttributes.addFlashAttribute("message", "You have been logged out");
        return "redirect:/login";
    }
}
