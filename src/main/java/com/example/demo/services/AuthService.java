package com.example.demo.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.ForgotPasswordRequest;
import com.example.demo.DTO.LoginRequest;
import com.example.demo.DTO.LoginResponse;
import com.example.demo.DTO.OtpRequest;
import com.example.demo.DTO.ResetPasswordRequest;
import com.example.demo.Entites.OTP;
import com.example.demo.Entites.User;
import com.example.demo.repos.OTPRepo;
import com.example.demo.repos.UserRepo;
import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.JwtService;

@Service
public class AuthService {

	 @Autowired
	    private AuthenticationManager manager;

	    @Autowired
	    private JwtService jwtService;

	    @Autowired
	    private CustomUserDetailsService userDetailsService;
	    
	    @Autowired
	    private OTPRepo otpRepository;

	    @Autowired
	    private EmailService emailService;

	    @Autowired
	    private UserRepo userRepo;
	    
	    @Autowired
	    private PasswordEncoder passwordEncoder;
	    
	   
	    private String generateOtp() {
	        int otp = (int) (Math.random() * 900000) + 100000;
	        return String.valueOf(otp);
	    }
	    public String login(LoginRequest request) {

	        manager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        request.getUsername(),
	                        request.getPassword()));

	        User user = userRepo.findByUsername(request.getUsername())
	                .orElseThrow(() -> new RuntimeException("User Not Found"));

	        String otp = generateOtp();

	        OTP savedOtp = otpRepository.findByEmail(user.getEmail())
	                .orElse(new OTP());

	       
	        savedOtp.setEmail(user.getEmail());
	        savedOtp.setOtp(otp);
	        savedOtp.setExpiryTime(LocalDateTime.now().plusMinutes(5));

	        otpRepository.save(savedOtp);

	        emailService.sendEmail(
	        	    user.getEmail(),
	        	    "🔐 RazzStock Login Verification Code",
	        	    "Hello " + user.getUsername() + ",\n\n" +
	        	    "A login attempt was made to your RazzStock account.\n\n" +
	        	    "To complete your sign-in, please use the One-Time Password (OTP) below:\n\n" +
	        	    "🔑 OTP: " + otp + "\n\n" +
	        	    "This OTP is valid for the next 5 minutes and can only be used once.\n\n" +
	        	    "If this login attempt was made by you, simply enter the OTP to continue.\n\n" +
	        	    "If you did NOT attempt to log in, please ignore this email. Your account remains secure as long as you do not share this OTP with anyone.\n\n" +
	        	    "For your security:\n" +
	        	    "• Never share your OTP with anyone.\n" +
	        	    "• RazzStock will never ask for your OTP via phone, email, or message.\n" +
	        	    "• Use this OTP only on the official RazzStock application.\n\n" +
	        	    "Thank you for choosing RazzStock.\n\n" +
	        	    "Best Regards,\n" +
	        	    "RazzStock Team"
	        	);

	        return "OTP sent successfully";
	    }
	    public LoginResponse verifyOtp(OtpRequest request) {

	        OTP savedOtp = otpRepository.findByEmail(request.getEmail())
	                .orElseThrow(() -> new RuntimeException("OTP not found"));

	        // Check expiry
	        if (savedOtp.getExpiryTime().isBefore(LocalDateTime.now())) {
	            throw new RuntimeException("OTP Expired");
	        }

	        // Check OTP
	        if (!savedOtp.getOtp().equals(request.getOtp())) {
	            throw new RuntimeException("Invalid OTP");
	        }

	        // Find user
	        User user = userRepo.findByEmail(request.getEmail())
	                .orElseThrow(() -> new RuntimeException("User Not Found"));

	        // Load UserDetails
	        UserDetails userDetails =
	                userDetailsService.loadUserByUsername(user.getUsername());

	        // Generate JWT
	        String token = jwtService.generateToken(userDetails);

	        // Delete OTP so it can't be reused
	        otpRepository.delete(savedOtp);

	        return new LoginResponse(
	                token,
	                user.getUsername(),
	                user.getEmail(),
	                user.getRole().name()
	        );
	    }
	    public String forgotPassword(ForgotPasswordRequest request) {

	        User user = userRepo.findByEmail(request.getEmail())
	                .orElseThrow(() -> new RuntimeException("Email not found"));

	        String otp = generateOtp();

	        OTP savedOtp = otpRepository.findByEmail(request.getEmail())
	                .orElse(new OTP());

	        savedOtp.setEmail(request.getEmail());
	        savedOtp.setOtp(otp);
	        savedOtp.setExpiryTime(LocalDateTime.now().plusMinutes(5));

	        otpRepository.save(savedOtp);

	        emailService.sendEmail(
	        	    request.getEmail(),
	        	    "🔐 RazzStock Password Reset Verification Code",
	        	    "Hello,\n\n" +
	        	    "We received a request to reset the password for your RazzStock account.\n\n" +
	        	    "Your One-Time Password (OTP) is:\n\n" +
	        	    "🔑 OTP: " + otp + "\n\n" +
	        	    "This OTP is valid for the next 5 minutes and can only be used once.\n\n" +
	        	    "If you did not request a password reset, please ignore this email. Your account will remain secure, and no changes will be made.\n\n" +
	        	    "For your security:\n" +
	        	    "• Never share this OTP with anyone.\n" +
	        	    "• RazzStock will never ask for your OTP via phone or email.\n" +
	        	    "• Use this OTP only on the official RazzStock application.\n\n" +
	        	    "Thank you for choosing RazzStock.\n\n" +
	        	    "Best Regards,\n" +
	        	    "RazzStock Team"
	        	);

	        return "OTP sent successfully";
	    }
	

	    public String resetPassword(ResetPasswordRequest request) {

	        OTP savedOtp = otpRepository.findByEmail(request.getEmail())
	                .orElseThrow(() -> new RuntimeException("OTP not found"));

	        if (!savedOtp.getOtp().equals(request.getOtp())) {
	            throw new RuntimeException("Invalid OTP");
	        }

	        if (savedOtp.getExpiryTime().isBefore(LocalDateTime.now())) {
	            throw new RuntimeException("OTP Expired");
	        }

	        User user = userRepo.findByEmail(request.getEmail())
	                .orElseThrow(() -> new RuntimeException("User Not Found"));

	        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

	        userRepo.save(user);

	        otpRepository.delete(savedOtp);

	        return "Password Reset Successfully";
	    }
	
}
