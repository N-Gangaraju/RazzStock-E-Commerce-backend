package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entites.User;
import com.example.demo.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/users")
@Tag(name = "User API", description = "User registration and profile management")
public class UserController {
	@Autowired
	private UserService service;
	
	@Operation(
	        summary = "Register new user",
	        description = "Creates a new user account with CUSTOMER or ADMIN role."
	    )
	@PostMapping("/register")
	public User register(@RequestBody User user)
	{
		return service.register(user);
	}
	
 
}
