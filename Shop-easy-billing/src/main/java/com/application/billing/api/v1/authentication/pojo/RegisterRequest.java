package com.application.billing.api.v1.authentication.pojo;

import com.application.billing.api.v1.user.ApplicationRole;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private ApplicationRole applicationRole;

}
