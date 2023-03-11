package com.application.billing.api.v1.company;

import com.application.billing.api.v1.company.pojo.CreateCompanyBody;
import com.application.billing.api.v1.company.pojo.UpdateCompanyBody;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/company")
public class CompanyController {
    private final CompanyService service;

    @PostMapping("create")
    public Company createCompany(@RequestBody CreateCompanyBody createCompanyBody) {
        return service.createCompany(createCompanyBody);
    }

    @GetMapping("{ownerId}")
    public Company getCompany(@PathVariable("ownerId") String ownerId) {
        return service.getCompany(ownerId);
    }

    @PutMapping("{Id}")
    public Company updateCompany(@PathVariable("Id") String companyId, @RequestBody UpdateCompanyBody updateCompanyBody) {
        return service.updateCompany(companyId, updateCompanyBody);
    }
}
