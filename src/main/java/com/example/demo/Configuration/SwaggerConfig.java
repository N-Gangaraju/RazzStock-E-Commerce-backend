package com.example.demo.Configuration;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig 
{
	public OpenAPI openAPI()
	{
		return new OpenAPI()
				.info(new Info()
						.title("RazzStock - Full-Stack E-Commerce Platform")
						.description("RazzStock is a full-stack e-commerce platform that provides REST APIs for managing users, products, categories, suppliers, shopping carts, wishlists, orders, reviews, and admin operations. The platform is built with Spring Boot and provides secure and scalable APIs for seamless product and order management.")
						.version("1.0")
						.contact(new Contact()
								.name("Gangaraju")
								.email("nagamraju457@gmail.com")));
	}
}
