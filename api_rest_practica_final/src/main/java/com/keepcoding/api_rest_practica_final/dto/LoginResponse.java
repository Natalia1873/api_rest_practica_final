package com.keepcoding.api_rest_practica_final.dto;


public class LoginResponse {
    private String token;
    private long expiresIn;

    // Default constructor
    public LoginResponse() {
    }
    
    // Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
