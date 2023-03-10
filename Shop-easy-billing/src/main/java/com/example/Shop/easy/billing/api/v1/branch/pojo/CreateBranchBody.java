package com.example.Shop.easy.billing.api.v1.branch.pojo;

import lombok.Data;

@Data
public class CreateBranchBody
{
    private String name;
    private String shortName;
    private String companyId;

}
