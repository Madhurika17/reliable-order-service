package com.madhurika.reliableOrderService.dto;

import com.madhurika.reliableOrderService.enums.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequest {
    
    private String email;
    private String password;
    private Role role;
}
