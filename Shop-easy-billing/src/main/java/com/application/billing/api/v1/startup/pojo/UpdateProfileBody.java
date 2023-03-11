package com.application.billing.api.v1.startup.pojo;

import lombok.Data;

@Data
public class UpdateProfileBody {
    private String firstName;
    private String lastName;
    private String password;
}
