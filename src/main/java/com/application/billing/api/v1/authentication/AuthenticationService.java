package com.application.billing.api.v1.authentication;

import com.application.billing.api.v1.authentication.pojo.LoginRequest;
import com.application.billing.api.v1.authentication.pojo.AuthenticationResponse;
import com.application.billing.api.v1.authentication.pojo.RegisterRequest;
import com.application.billing.api.v1.errorresponse.ErrorResponse;
import com.application.billing.api.v1.user.User;
import com.application.billing.api.v1.user.UserRepository;
import com.application.billing.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService service;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;


    public AuthenticationResponse register(RegisterRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setApplicationRole(request.getApplicationRole());
        userRepository.save(user);
        String token = service.generateToken(new HashMap<>(), user);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(token);
        return response;
    }

    public AuthenticationResponse authenticate(LoginRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getUsername());
        if (userOptional.isEmpty()) {
            userOptional = userRepository.findByPhoneNumber(request.getUsername());
        }
        User user = userOptional.orElseThrow(() -> {
            throw new ErrorResponse(HttpStatus.NOT_FOUND, "Invalid username or password");
        });
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getId(),
                        request.getPassword()
                )
        );
        String token = service.generateToken(new HashMap<>(), user);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(token);
        return response;
    }
}



