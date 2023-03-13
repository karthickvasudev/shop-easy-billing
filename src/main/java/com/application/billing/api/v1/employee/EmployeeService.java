package com.application.billing.api.v1.employee;

import com.application.billing.Utils.CommonUtils;
import com.application.billing.api.v1.employee.pojo.CreateEmployeeBody;
import com.application.billing.api.v1.user.User;
import com.application.billing.api.v1.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final CommonUtils commonUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    public Map<String, String> createEmployee(CreateEmployeeBody createEmployeeBody) {
        String invitePassword = commonUtils.generateRandomString(7);
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setFirstName(createEmployeeBody.getFirstName());
        user.setLastName(createEmployeeBody.getLastName());
        user.setEmail(createEmployeeBody.getEmail());
        user.setPhoneNumber(createEmployeeBody.getPhoneNumber());
        user.setPassword(encoder.encode(invitePassword));
        user.setApplicationRole(createEmployeeBody.getApplicationRole());
        user.setIsInvite(true);
        user.setIsProfileUpdated(false);
        user.setCompanyId(createEmployeeBody.getCompanyId());
        user.setBranches(createEmployeeBody.getBranches());
        userRepository.save(user);
        return Map.of("invitePassword",invitePassword);
    }
}
