package com.madhurika.reliableOrderService.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.madhurika.reliableOrderService.model.User;
import com.madhurika.reliableOrderService.repository.UserRepo;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
    @Autowired
    private UserRepo userRepo;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found:"+username)); 
        return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                .password(user.getPasswordHash())
                .roles(user.getRole().name())
                .build();
    }
}