package com.eazybytes.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

/**
 * @author shubham
 *
 */
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImp")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts Microservics Rest API Documnetation", 
				description = "This accounts service in whihch we are dealing",
				version = "v1",
				contact = @Contact(
						name = "Shubham Pandey",
						email = "shbteam@gmail.com",
						url = "helpinflusoft.com"
						),
				license = @License(
						name = "Apache2.0",
						url = "xyz.com"
						)
				
				),
		externalDocs =@ExternalDocumentation(
				description = "bank account vist below url for knowing more ",
				url = "xyz.com"
				)
		)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
