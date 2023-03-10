package com.example.Shop.easy.billing.security.auth;

import com.example.Shop.easy.billing.security.users.ApplicationRole;
import lombok.Data;

@Data
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private ApplicationRole applicationRole;

}
