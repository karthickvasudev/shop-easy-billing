package com.application.billing.api.v1.profile.pojo;

import com.application.billing.api.v1.company.Company;
import com.application.billing.api.v1.user.ApplicationRole;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProfileResponse {
    private String id;
    private String firstName;
    private String lastName;
    private ApplicationRole applicationRole;
    private String email;
    private String phoneNumber;
    private Company company;

    private Boolean isInvite;
    private Boolean isProfileUpdated;

}
