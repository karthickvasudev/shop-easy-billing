package com.application.billing.api.v1.company.pojo;

import lombok.Data;

@Data
public class CreateCompanyBody
{

    private String name;
    private HomeBranch homeBranch;

    //created as static class and refer postman body
    @Data
    public static class HomeBranch{
        private String name;
        private String shortName;


    }
}
