package com.application.billing.api.v1.startup.pojo;

import lombok.Data;

@Data
public class InviteBody {
    private String email;
    private String phoneNumber;
}
