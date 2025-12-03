package com.waterpocket.backend.controller;

import com.waterpocket.backend.model.User;
import com.waterpocket.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // Allow Frontend Access
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        // 1. Check if email exists
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("User already exists");
        }
        // 2. Set defaults
        user.setRole("user");
        if (user.getWalletBalance() == null) user.setWalletBalance(0.0);
        
        return userRepository.save(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        // 1. HARDCODED ADMIN BACKDOOR (Ensures Admin can ALWAYS login)
        if ("prasannasomasekar@gmail.com".equals(email) && "prasanna123".equals(password)) {
            User admin = new User();
            admin.setId("ADMIN-001");
            admin.setName("Prasanna (Owner)");
            admin.setEmail(email);
            admin.setRole("admin");
            admin.setWalletBalance(0.0);
            return admin;
        }

        // 2. CHECK DATABASE FOR USERS
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        
        return null; // Login failed
    }
}