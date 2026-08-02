package com.example.demo.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entites.OTP;

public interface OTPRepo  extends JpaRepository<OTP, Integer>
{
	Optional<OTP>findByEmail(String email);
}
