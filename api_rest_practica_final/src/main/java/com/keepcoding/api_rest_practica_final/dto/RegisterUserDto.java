package com.keepcoding.api_rest_practica_final.dto;

public class RegisterUserDto {
	
	private String email;
	private String password;
	private String fullName;
	
	// Default constructor
    public RegisterUserDto() {
    }

    // Getters and Setters
	public String getEmail() {
		return email;
	}
	public RegisterUserDto setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public RegisterUserDto setPassword(String password) {
		this.password = password;
		return this;
	}
	public String getFullName() {
		return fullName;
	}
	public RegisterUserDto setFullName(String fullName) {
		this.fullName = fullName;
		return this;
	}

}
