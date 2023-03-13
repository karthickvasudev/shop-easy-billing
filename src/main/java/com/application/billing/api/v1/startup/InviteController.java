package com.application.billing.api.v1.startup;

import com.application.billing.api.v1.profile.pojo.ProfileResponse;
import com.application.billing.api.v1.startup.pojo.UpdateProfileBody;
import com.application.billing.api.v1.startup.pojo.InviteBody;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/invite")
@AllArgsConstructor
public class InviteController {
    private final InviteService service;

    @PostMapping("owner")
    public Map<String, String> generateInviteToken(@RequestBody InviteBody body) {
        return service.inviteOwner(body);
    }

    @PutMapping("updateProfile")
    public ProfileResponse updateUserProfileForInvite(@RequestBody UpdateProfileBody body){
        return service.updateUserProfileForInvite(body);
    }
}
