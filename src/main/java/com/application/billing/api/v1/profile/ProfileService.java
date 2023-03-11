package com.application.billing.api.v1.profile;

import com.application.billing.Utils.CurrentUserDetails;
import com.application.billing.api.v1.company.Company;
import com.application.billing.api.v1.company.CompanyRepository;
import com.application.billing.api.v1.errorresponse.ErrorResponse;
import com.application.billing.api.v1.profile.pojo.ProfileResponse;
import com.application.billing.api.v1.user.User;
import com.application.billing.api.v1.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class ProfileService {
    private final UserRepository repository;
    private final CurrentUserDetails currentUserDetails;
    private final CompanyRepository companyRepository;

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
        Optional<Company> optionalCompany = companyRepository.findByOwnerId(user.getId());
        ProfileResponse profileResponse = new ProfileResponse();
        profileResponse.setId(user.getId());
        profileResponse.setFirstName(user.getFirstName());
        profileResponse.setLastName(user.getLastName());
        profileResponse.setEmail(user.getEmail());
        profileResponse.setPhoneNumber(user.getPhoneNumber());
        profileResponse.setApplicationRole(user.getApplicationRole());
        profileResponse.setCompany(optionalCompany.orElse(null));
        profileResponse.setIsInvite(user.getIsInvite());
        profileResponse.setIsProfileUpdated(user.getIsProfileUpdated());
        log.info("profile response : {}", profileResponse);
        return profileResponse;
    }
}


