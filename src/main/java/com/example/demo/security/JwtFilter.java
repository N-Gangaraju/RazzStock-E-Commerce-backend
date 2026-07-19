package com.example.demo.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter  extends OncePerRequestFilter{
	@Autowired
	private JwtService jwtservice;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	public JwtFilter() {
		System.out.println("hello i am jwt");
	}
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("JwtFilter Started");
		
		String authHeader = request.getHeader("Authorization");
		System.out.println("Header = " + authHeader);
		if(authHeader == null)
		{
			filterChain.doFilter(request, response);
			return;
		}
		if(!authHeader.startsWith("Bearer "))
		{
			filterChain.doFilter(request, response);
			return;
		}
		String token = authHeader.substring(7);
		System.out.println("token="+token);
		String username = jwtservice.extractUsername(token);
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
		if(jwtservice.validateToken(token, userDetails))
		{
			UsernamePasswordAuthenticationToken authentication =
			        new UsernamePasswordAuthenticationToken(
			                userDetails,
			                null,
			                userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			System.out.println("Token is valid");
			System.out.println("Authentication Set Successfully");
		}
		filterChain.doFilter(request, response);
		
		
		
		
	}
	
	

}
