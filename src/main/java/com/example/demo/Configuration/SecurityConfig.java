package com.example.demo.Configuration;

import java.util.List;
import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.demo.security.JwtFilter;

@Configuration
public class SecurityConfig {
	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean
	public SecurityFilterChain chain(HttpSecurity http) throws Exception{
		
		http
		.cors(Customizer.withDefaults())
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth

        	    .requestMatchers(
        	            "/swagger-ui/**",
        	            "/v3/api-docs/**",
        	            "/users/register",
        	            "/auth/login",
        	            "/auth/verify-otp",
        	            "/products/*/upload-image",
        	            "/uploads/**",
        	            "/test-email",
        	            "/categories/**",
        	            "/auth/forgot-password",
        	            "/auth/reset-password",
        	            "/error",
        	            "/products/**",
        	            "/reviews/product/**",
        	            "/reviews/product/*/rating"
        	            
        	    ).permitAll()

        	    //CUSTOMER + ADMIN
        	    .requestMatchers(
        	    		"/orders/myorders",
        	    		"/orders/*/invoice"
        	    		).hasAnyRole("ADMIN","CUSTOMER")
        	    // ADMIN only------------------------------------
        	    .requestMatchers(
        	            "/products/add",
        	            "/products/update/**",
        	            "/products/delete/**",
        	            "/categories/**",
        	            "/suppliers/**",
        	            "/orders",
        	            "/orders/{orderId}/status",
        	            "/dashboard/**",
        	            "/categories/add",
        	            "/categories/update/**",
        	            "/categories/delete/**",
        	            "/reviews/delete/**"
        	           
        	    ).hasRole("ADMIN")

        	    // CUSTOMER only ----------------------------
                .requestMatchers(
                		"/reviews/add",
                		"/reviews/update/**",
                		"/reviews/delete/**",
                        "/cart/**",
                        "/orders/cancel/**"
                ).hasRole("CUSTOMER")

        	    // ADMIN and CUSTOMER -----------------------
        	    .requestMatchers(
        	            "/products",
        	            "/products/**"
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
	@Bean
	CorsConfigurationSource corsConfigurationSource() {

	    CorsConfiguration configuration = new CorsConfiguration();

	    configuration.setAllowedOrigins(List.of("http://localhost:5173","http://localhost:5174"));

	    configuration.setAllowedMethods(List.of("*"));

	    configuration.setAllowedHeaders(List.of("*"));

	    configuration.setAllowCredentials(true);

	    UrlBasedCorsConfigurationSource source =
	            new UrlBasedCorsConfigurationSource();

	    source.registerCorsConfiguration("/**", configuration);

	    return source;
	}
	
	

}
