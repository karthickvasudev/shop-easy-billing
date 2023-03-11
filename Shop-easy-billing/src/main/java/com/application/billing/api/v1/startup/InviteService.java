package com.application.billing.api.v1.startup;

import com.application.billing.Utils.CommonUtils;
import com.application.billing.Utils.CurrentUserDetails;
import com.application.billing.api.v1.errorresponse.ErrorResponse;
import com.application.billing.api.v1.startup.pojo.UpdateProfileBody;
import com.application.billing.api.v1.startup.pojo.InviteBody;
import com.application.billing.api.v1.user.ApplicationRole;
import com.application.billing.api.v1.user.User;
import com.application.billing.api.v1.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InviteService {
    private final UserRepository userRepository;
    private final CommonUtils commonUtils;
    private final PasswordEncoder encoder;
    private final CurrentUserDetails currentUserDetails;

    public Map<String, String> inviteOwner(InviteBody body) {
        String invitePassword = commonUtils.generateRandomString(7);

        User user = new User();
        user.setEmail(body.getEmail());
        user.setPhoneNumber(body.getPhoneNumber());
        user.setIsInvite(true);
        user.setIsProfileUpdated(false);
        user.setApplicationRole(ApplicationRole.OWNER);
        user.setPassword(encoder.encode(invitePassword));
        userRepository.save(user);
        return Map.of("invitePassword", invitePassword);
    }

    public User updateUserProfileForInvite(UpdateProfileBody body) {
        Optional<User> userOptional = userRepository.findById(currentUserDetails.getId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setFirstName(body.getFirstName());
            user.setLastName(body.getLastName());
            user.setPassword(encoder.encode(body.getPassword()));
            user.setIsProfileUpdated(true);
            return userRepository.save(user);
        }
        throw new ErrorResponse(HttpStatus.NOT_FOUND, "user not found");
    }
}
