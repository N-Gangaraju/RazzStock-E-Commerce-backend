package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.ForgotPasswordRequest;
import com.example.demo.DTO.LoginRequest;
import com.example.demo.DTO.LoginResponse;
import com.example.demo.DTO.OtpRequest;
import com.example.demo.DTO.ResetPasswordRequest;
import com.example.demo.repos.UserRepo;
import com.example.demo.services.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication API", description = "Authentication and authorization endpoints")
public class AuthenticationController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private UserRepo userRepo;
	
	@Operation(
	        summary = "Login user",
	        description = "Authenticates the user and returns a JWT token."
	    )
	@PostMapping("/login")
	public String login( @Valid @RequestBody LoginRequest request)
	{
		return authService.login(request);
	}
	@PostMapping("/verify-otp")
	public LoginResponse verifyOtp(@RequestBody OtpRequest request) {
	    return authService.verifyOtp(request);
	}
	@PostMapping("/forgot-password")
	public String forgotPassword(@RequestBody ForgotPasswordRequest request) {
	    return authService.forgotPassword(request);
	}

	@PostMapping("/reset-password")
	public String resetPassword(@RequestBody ResetPasswordRequest request) {
	    return authService.resetPassword(request);
	}
	
	

}
