package com.keepcoding.api_rest_practica_final.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.keepcoding.api_rest_practica_final.dto.LoginUserDto;
import com.keepcoding.api_rest_practica_final.dto.RegisterUserDto;
import com.keepcoding.api_rest_practica_final.entity.User;
import com.keepcoding.api_rest_practica_final.repository.UserRepository;


@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }
    
    public User signup(RegisterUserDto input) {
        if (userRepository.findByEmail(input.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto loginDto) {
        try {
            System.out.println("Attempting authentication for user: " + loginDto.getEmail());
            
            // Find user first
            User user = userRepository.findByEmail(loginDto.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            
            // Verify password
            if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
                throw new RuntimeException("Invalid credentials");
            }
            
            // Authenticate
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(),
                            loginDto.getPassword()
                    )
            );
            
            if (authentication.isAuthenticated()) {
                System.out.println("Authentication successful");
                return user;
            } else {
                throw new RuntimeException("Authentication failed");
            }
        } catch (Exception e) {
            System.out.println("Authentication error: " + e.getMessage());
            throw new RuntimeException("Authentication failed: " + e.getMessage());
        }
    }
}
