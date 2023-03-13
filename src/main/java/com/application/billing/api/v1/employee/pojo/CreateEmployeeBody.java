package com.application.billing.api.v1.employee.pojo;

import com.application.billing.api.v1.user.ApplicationRole;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class CreateEmployeeBody {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private ApplicationRole applicationRole;
    private Boolean isInvite;
    private Boolean isProfileUpdated;
    private String companyId;
    private List<String> branches;
}
