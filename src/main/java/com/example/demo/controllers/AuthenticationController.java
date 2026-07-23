package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.LoginRequest;
import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.JwtService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication API", description = "Authentication and authorization endpoints")
public class AuthenticationController {
	@Autowired
	private AuthenticationManager manager;
	@Autowired
	private JwtService jwtservice;
	@Autowired
	private CustomUserDetailsService service;
	
	@Operation(
	        summary = "Login user",
	        description = "Authenticates the user and returns a JWT token."
	    )
	@PostMapping("/login")
	public String login( @Valid @RequestBody LoginRequest request)
	{
		manager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		UserDetails userDetails = service.loadUserByUsername(request.getUsername());
		return jwtservice.generateToken(userDetails);
	}
	
	

}
