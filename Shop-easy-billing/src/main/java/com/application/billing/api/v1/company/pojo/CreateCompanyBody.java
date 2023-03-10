package com.application.billing.api.v1.company.pojo;

import lombok.Data;

@Data
public class CreateCompanyBody
{

    private String name;
    private String ownerId;
}
