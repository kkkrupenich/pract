package com.example.spring.services;

import com.example.spring.security.JwtUtil;
import com.example.spring.models.RegisterModel;
import com.example.spring.responses.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthenticationService(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    public AuthenticationResponse signIn(String email, String passwordHash) {
        var user = userService.getUserByEmailAndPassword(email, passwordHash);
        var token = jwtUtil.generateToken(user.getId(), user.getEmail());

        return new AuthenticationResponse(user.getId(), token);
    }

    public AuthenticationResponse signUp(RegisterModel registerModel) {
        var user = userService.addUser(registerModel);
        var token = jwtUtil.generateToken(user.getId(), user.getEmail());

        return new AuthenticationResponse(user.getId(), token);
    }
}
