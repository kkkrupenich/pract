package com.example.spring.services;

import com.example.spring.security.JwtUtil;
import com.example.spring.models.RegisterModel;
import com.example.spring.responses.AuthenticationResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthenticationService(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    public AuthenticationResponse signIn(String email, String passwordHash) {
        var user = userService.getUserByEmailAndPassword(email, passwordHash);
        if (user != null) {
            var token = jwtUtil.generateToken(user.getId());
            return new AuthenticationResponse(user.getId(), token);
        }
        else {
            return null;
        }

    }

    public AuthenticationResponse signUp(RegisterModel registerModel) {
        var user = userService.addUser(registerModel);
        var token = jwtUtil.generateToken(user.getId());

        return new AuthenticationResponse(user.getId(), token);
    }
}
