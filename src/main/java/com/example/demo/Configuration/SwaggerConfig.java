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
						.description("REST API for managing Products, Categories and Suppliers")
						.version("1.0")
						.contact(new Contact()
								.name("Gangaraju")
								.email("nagamraju457@gmail.com")));
	}
}
