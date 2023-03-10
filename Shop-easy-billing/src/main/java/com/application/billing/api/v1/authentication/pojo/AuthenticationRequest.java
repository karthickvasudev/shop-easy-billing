package com.application.billing.api.v1.authentication.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AuthenticationRequest {
    private String username;
    private String password;
}
