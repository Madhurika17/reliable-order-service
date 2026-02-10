package com.madhurika.reliableOrderService.controller;

import com.madhurika.reliableOrderService.service.UserService;
import com.madhurika.reliableOrderService.model.User;
import com.madhurika.reliableOrderService.repository.UserRepo;
import com.madhurika.reliableOrderService.dto.RegisterUserRequest;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepo userRepo;

    

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterUserRequest request) {
        //TODO: process POST request
        
        User user = userService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()) return ResponseEntity.ok(user.get());
        else return ResponseEntity.notFound().build();
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepo.findAll();
        return ResponseEntity.ok(users);
    }

}
