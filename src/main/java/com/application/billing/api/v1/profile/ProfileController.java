package com.application.billing.api.v1.profile;

import com.application.billing.api.v1.profile.pojo.ProfileResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/profile")
@AllArgsConstructor
public class ProfileController {
    private final ProfileService service;

    @GetMapping()
    public ProfileResponse getUserProfile() {
        return service.getUserProfile();
    }
}
