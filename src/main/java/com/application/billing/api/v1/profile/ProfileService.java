package com.application.billing.api.v1.profile;

import com.application.billing.Utils.CurrentUserDetails;
import com.application.billing.api.v1.branch.Branch;
import com.application.billing.api.v1.branch.BranchRepository;
import com.application.billing.api.v1.company.Company;
import com.application.billing.api.v1.company.CompanyRepository;
import com.application.billing.api.v1.company.CompanyService;
import com.application.billing.api.v1.errorresponse.ErrorResponse;
import com.application.billing.api.v1.profile.pojo.ProfileResponse;
import com.application.billing.api.v1.user.ApplicationRole;
import com.application.billing.api.v1.user.User;
import com.application.billing.api.v1.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class ProfileService {
    private final UserRepository repository;
    private final CurrentUserDetails currentUserDetails;
    private final CompanyRepository companyRepository;
    private final CompanyService companyService;
    private final BranchRepository branchRepository;


    public ProfileResponse getUserProfile() {
        Optional<User> user = repository.findById(currentUserDetails.getId());
        if (user.isEmpty()) {
            throw new ErrorResponse(HttpStatus.NOT_FOUND, "User not found");
        }
        return profileParser(user.get());
    }

    public ProfileResponse getUserProfileById(String id) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            throw new ErrorResponse(HttpStatus.NOT_FOUND, "User not found");
        }
        return profileParser(user.get());
    }

    private ProfileResponse profileParser(User user) {
        ProfileResponse profileResponse = new ProfileResponse();
        profileResponse.setId(user.getId());
        profileResponse.setFirstName(user.getFirstName());
        profileResponse.setLastName(user.getLastName());
        profileResponse.setEmail(user.getEmail());
        profileResponse.setPhoneNumber(user.getPhoneNumber());
        profileResponse.setApplicationRole(user.getApplicationRole());
        profileResponse.setIsInvite(user.getIsInvite());
        profileResponse.setIsProfileUpdated(user.getIsProfileUpdated());

        if(user.getApplicationRole().equals(ApplicationRole.OWNER)) {
            Optional<Company> optionalCompany = companyRepository.findByOwnerId(user.getId());
            profileResponse.setCompany(optionalCompany.orElse(null));
            try {
                Company company = companyService.getCompanyByOwnerId(user.getId());
                profileResponse.setCompany(company);
            }catch (Exception e){

            }
        }else {
            Company company = companyService.getCompanyDetailsWithBranchesByCompanyId(user.getCompanyId());
            List<Branch> accessedBranches = user.getBranches()
                    .stream().map(branchId -> company.getBranches().stream()
                    .filter(branch -> branch.getId().equals(branchId)).findFirst().get())
                    .collect(Collectors.toList());
            company.setBranches(accessedBranches);
            profileResponse.setCompany(company);
        }
        log.info("profile response : {}", profileResponse);
        return profileResponse;
    }
}


