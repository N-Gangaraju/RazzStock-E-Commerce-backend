package com.example.demo.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private static final String SECRET_KEY = "nagamgangarajusecretkey7702592496";
	
	//It converts string type of secretkey to bytes type  
	private SecretKey getSignKey() 
	{
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
	}
	
	public String generateToken(UserDetails details)
	{
		return Jwts.builder()
				.subject(details.getUsername())
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis()+ 1000*60*60))
				.signWith(getSignKey())
				.compact();
	}
	public String extractUsername(String token)
	{
		return extractClaims(token).getSubject();
	}
	public Claims extractClaims(String token) {
	    return Jwts.parser()
	            .verifyWith(getSignKey())
	            .build()
	            .parseSignedClaims(token)
	            .getPayload();
	}
	private boolean isTokenExpired(String token) {
	    return extractClaims(token)
	            .getExpiration()
	            .before(new Date());
	}
	public boolean validateToken(String token, UserDetails userDetails) {

	    String username = extractUsername(token);

	    return username.equals(userDetails.getUsername())
	            && !isTokenExpired(token);
	}
}
