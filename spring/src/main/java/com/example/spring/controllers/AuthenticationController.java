package com.example.spring.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.models.LoginModel;
import com.example.spring.models.RegisterModel;
import com.example.spring.services.AuthenticationService;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("signin")
    public ResponseEntity<Object> signIn(@RequestBody LoginModel loginModel) {
        var authenticationResponse = authenticationService.signIn(loginModel.getEmail(), loginModel.getPassword());

        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }

    @PostMapping("signup")
    public ResponseEntity<Object> signUp(@RequestBody RegisterModel registerModel) {
        var authenticationResponse = authenticationService.signUp(registerModel);

        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }
}