package com.keepcoding.api_rest_practica_final.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keepcoding.api_rest_practica_final.dto.LoginResponse;
import com.keepcoding.api_rest_practica_final.dto.LoginUserDto;
import com.keepcoding.api_rest_practica_final.dto.RegisterUserDto;
import com.keepcoding.api_rest_practica_final.entity.User;
import com.keepcoding.api_rest_practica_final.service.AuthenticationService;
import com.keepcoding.api_rest_practica_final.service.JwtService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	private final JwtService jwtService;
	private final AuthenticationService authenticationService;
	
	
	
	public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authentication(@RequestBody LoginUserDto loginUserDto){
    	User authenticateUser = authenticationService.authenticate(loginUserDto);
    	
    	String jwtToken = jwtService.generateToken(authenticateUser);
    	
    	LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
     }
 

    }

   