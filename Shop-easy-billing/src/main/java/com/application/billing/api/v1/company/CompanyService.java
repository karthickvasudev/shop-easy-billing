package com.application.billing.api.v1.company;

import com.application.billing.api.v1.company.pojo.CreateCompanyBody;
import com.application.billing.api.v1.company.pojo.UpdateCompanyBody;
import com.application.billing.api.v1.branch.Branch;
import com.application.billing.api.v1.branch.BranchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final BranchService branchService;

    public Company createCompany(CreateCompanyBody createCompanyBody) {
        Company company = new Company();
        company.setId(UUID.randomUUID().toString());
        company.setName(createCompanyBody.getName());
        company.setOwnerId(createCompanyBody.getOwnerId());
        company.setActive(true);
        company.setCreatedOn(LocalDateTime.now());
        Company save = companyRepository.save(company);
        return save;
    }

    public Company getCompany(String ownerId) {
        Optional<Company> optionalCompany = companyRepository.findByOwnerId(ownerId);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            String companyId = company.getId();
            List<Branch> branchesByCompanyId = branchService.getBranchesByCompanyId(companyId);
            company.setBranches(branchesByCompanyId);
            return company;
        }
        throw new RuntimeException("optionalCompany not found");
    }

    public Company updateCompany(String companyId, UpdateCompanyBody updateCompanyBody) {
        Optional<Company> update = companyRepository.findById(companyId);
        if (update.isPresent()) {
            Company company = update.get();
            company.setName(updateCompanyBody.getName());
            company.setUpdatedOn(LocalDateTime.now());
            Company save = companyRepository.save(company);
            return save;
        }
        throw new RuntimeException("company not found");
    }
}
