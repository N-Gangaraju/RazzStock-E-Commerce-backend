package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.EmailService;

@RestController
public class EmailController 
{

	@Autowired
	private EmailService emailService;
	
	
	 @GetMapping("/test-email")
	    public String sendTestEmail() 
	 {

	        emailService.sendEmail(
	                "nagamraju457@gmail.com",  
	                "SmartShelf Email Test",
	                "Congratulations! Your SmartShelf Email Service is working successfully."
	        );

	        return "Email Sent Successfully";
	 }
}
