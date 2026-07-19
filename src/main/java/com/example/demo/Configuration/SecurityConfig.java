package com.example.demo.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.security.JwtFilter;

@Configuration
public class SecurityConfig {
	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean
	public SecurityFilterChain chain(HttpSecurity http) throws Exception{
		
		http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth

        	    .requestMatchers(
        	            "/swagger-ui/**",
        	            "/v3/api-docs/**",
        	            "/users/register",
        	            "/auth/login"
        	    ).permitAll()

        	    // ADMIN only------------------------------------
        	    .requestMatchers(
        	            "/products/add",
        	            "/products/update/**",
        	            "/products/delete/**",
        	            "/categories/**",
        	            "/suppliers/**",
        	            "/orders/**"
        	    ).hasRole("ADMIN")

        	    // CUSTOMER only ----------------------------
                .requestMatchers(
                        "/cart/**"
                ).hasRole("CUSTOMER")

        	    // ADMIN and CUSTOMER -----------------------
        	    .requestMatchers(
        	            "/products",
        	            "/products/**",
        	            "/orders/myorders"
        	    ).hasAnyRole("ADMIN","CUSTOMER")

        	    .anyRequest().authenticated()
        	).addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);

     
    return http.build();
	}
	@Bean
	public PasswordEncoder password() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationManager manager(AuthenticationConfiguration config) throws Exception
	{
		return config.getAuthenticationManager();
	}
	

}
