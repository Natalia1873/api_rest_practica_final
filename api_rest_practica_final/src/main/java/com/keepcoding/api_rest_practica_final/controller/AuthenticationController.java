package com.keepcoding.api_rest_practica_final.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.keepcoding.api_rest_practica_final.dto.LoginResponse;
import com.keepcoding.api_rest_practica_final.dto.LoginUserDto;
import com.keepcoding.api_rest_practica_final.dto.RegisterUserDto;
import com.keepcoding.api_rest_practica_final.entity.User;
import com.keepcoding.api_rest_practica_final.service.AuthenticationService;
import com.keepcoding.api_rest_practica_final.service.JwtService;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    public AuthenticationController(AuthenticationService authenticationService, JwtService jwtService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/signup")
    public ResponseEntity<LoginResponse> signup(@RequestBody RegisterUserDto registerUserDto) {
        try {
            User registeredUser = authenticationService.signup(registerUserDto);
            String token = jwtService.generateToken(registeredUser);
            
            LoginResponse response = new LoginResponse();
            response.setToken(token);
            response.setExpiresIn(jwtService.getExpirationTime());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginUserDto loginUserDto) {
        try {
            System.out.println("Login attempt for email: " + loginUserDto.getEmail()); // Debug log
            
            User user = authenticationService.authenticate(loginUserDto);
            String token = jwtService.generateToken(user);
            
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(token);
            loginResponse.setExpiresIn(jwtService.getExpirationTime());
            
            return ResponseEntity.ok(loginResponse);
            
        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage()); // Debug log
            return ResponseEntity.badRequest().body(null);
        }
    }
}
