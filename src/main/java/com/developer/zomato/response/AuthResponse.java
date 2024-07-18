package com.developer.zomato.response;

import com.developer.zomato.entity.Role;
import lombok.Data;

@Data
public class AuthResponse {

    private String jwt;
    private Role role;
    private String message;
}
