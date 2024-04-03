package com.example.spring.responses;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private Long id;
    private String token;

    public AuthenticationResponse(Long id, String token) {
        this.id = id;
        this.token = token;
    } 
}