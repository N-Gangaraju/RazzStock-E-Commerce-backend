package com.example.demo.Entites;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OTP {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    private String email;

	    private String otp;

	    private LocalDateTime expiryTime;

		public OTP() {
			super();
			// TODO Auto-generated constructor stub
		}

		public OTP(Integer id, String email, String otp, LocalDateTime expiryTime) {
			super();
			this.id = id;
			this.email = email;
			this.otp = otp;
			this.expiryTime = expiryTime;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getOtp() {
			return otp;
		}

		public void setOtp(String otp) {
			this.otp = otp;
		}

		public LocalDateTime getExpiryTime() {
			return expiryTime;
		}

		public void setExpiryTime(LocalDateTime expiryTime) {
			this.expiryTime = expiryTime;
		}
	    
	    

}
