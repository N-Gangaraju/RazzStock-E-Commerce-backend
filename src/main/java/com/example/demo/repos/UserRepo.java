package com.example.demo.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entites.User;

public interface UserRepo extends JpaRepository<User,Integer> {
	Optional<User>findByUsername(String username);
	Optional<User>findByEmail(String email);

}
