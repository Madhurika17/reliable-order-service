package com.madhurika.reliableOrderService.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madhurika.reliableOrderService.dto.RegisterUserRequest;
import com.madhurika.reliableOrderService.model.User;
import com.madhurika.reliableOrderService.repository.UserRepo;

@Service
public class UserService {
    
    @Autowired
    private UserRepo userRepo;

    public User registerUser(RegisterUserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        // Hash the password before saving
        user.setPasswordHash("HASHED_" +(request.getPassword()));
        user.setCreatedAt(LocalDateTime.now());

        return userRepo.save(user);
    }
}
