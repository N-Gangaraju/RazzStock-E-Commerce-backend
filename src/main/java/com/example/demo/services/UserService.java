package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entites.User;
import com.example.demo.repos.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo repo;
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private EmailService emailService;
	
	public User register(User user)
	{
		if(repo.findByUsername(user.getUsername()).isPresent())
		{
			throw new RuntimeException("Username Already Exists");
		}
		if(repo.findByEmail(user.getEmail()).isPresent())
		{
			throw new RuntimeException("Email already Exists");
		}
		user.setPassword(encoder.encode(user.getPassword()));
		User savedUser = repo.save(user);
		
		emailService.sendWelcomeEmail(savedUser.getUsername(),savedUser.getEmail());
		
		return savedUser;
	}
	public List<User>getAllUsers(){
		return repo.findAll();
	}

}
