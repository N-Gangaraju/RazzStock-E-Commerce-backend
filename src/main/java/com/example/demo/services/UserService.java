package com.example.demo.services;

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
	
	public User register(User user)
	{
		if(repo.findByUsername(user.getUsername()).isPresent())
		{
			throw new RuntimeException("Username already Exists");
		}
		if(repo.findByEmail(user.getEmail()).isPresent())
		{
			throw new RuntimeException("Email already Exists");
		}
		user.setPassword(encoder.encode(user.getPassword()));
		return repo.save(user);
	}

}
