package com.example.mvc.controller;

import com.example.mvc.model.User;
import com.example.mvc.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/list";
    }

    @GetMapping("/form")
    public String userForm(Model model) {
        model.addAttribute("user", new User());
        return "users/form";
    }

    @PostMapping
    public String saveUser(@Valid @ModelAttribute("user") User user, 
                         BindingResult bindingResult,
                         @RequestParam(value = "profileImage", required = false) MultipartFile profileImage,
                         RedirectAttributes redirectAttributes) {
        
        if (bindingResult.hasErrors()) {
            return "users/form";
        }
        
        // Handle profile image upload if present
        if (profileImage != null && !profileImage.isEmpty()) {
            try {
                // Create the upload directory if it doesn't exist
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Generate a unique file name
                String uniqueFileName = UUID.randomUUID().toString() + "_" + profileImage.getOriginalFilename();
                
                // Save the file
                Path filePath = uploadPath.resolve(uniqueFileName);
                Files.copy(profileImage.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                // Update user with profile picture file name
                user.setProfilePicture(uniqueFileName);
                
            } catch (IOException e) {
                redirectAttributes.addFlashAttribute("error", "Failed to upload profile image: " + e.getMessage());
                return "users/form";
            }
        }
        
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("message", "User saved successfully!");
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
        
        model.addAttribute("user", user);
        return "users/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        userService.deleteUser(id);
        redirectAttributes.addFlashAttribute("message", "User deleted successfully!");
        return "redirect:/users";
    }
}
