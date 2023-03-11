package com.application.billing.api.v1.company;

import com.application.billing.Utils.CurrentUserDetails;
import com.application.billing.api.v1.branch.Branch;
import com.application.billing.api.v1.branch.BranchService;
import com.application.billing.api.v1.company.pojo.CreateCompanyBody;
import com.application.billing.api.v1.company.pojo.UpdateCompanyBody;
import com.application.billing.api.v1.errorresponse.ErrorResponse;
import com.application.billing.api.v1.user.User;
import com.application.billing.api.v1.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final BranchService branchService;
    private final CurrentUserDetails currentUserDetails;

    public Company createCompany(CreateCompanyBody createCompanyBody) {
        Optional<User> userOptional = userRepository.findById(currentUserDetails.getId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getIsInvite() && user.getIsProfileUpdated()) {
                Company company = new Company();
                company.setId(UUID.randomUUID().toString());
                company.setName(createCompanyBody.getName());
                company.setOwnerId(currentUserDetails.getId());
                company.setActive(true);
                company.setCreatedOn(LocalDateTime.now());
                Company save = companyRepository.save(company);

                user.setIsInvite(false);
                userRepository.save(user);
                return save;
            } else {
                String errorMsg = !user.getIsInvite() ? "invitation expired" : "profile not updated";
                throw new ErrorResponse(HttpStatus.NOT_ACCEPTABLE, errorMsg);
            }
        } else {
            throw new ErrorResponse(HttpStatus.NOT_FOUND, "user not found");
        }

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
        throw new RuntimeException("Company not found");
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
