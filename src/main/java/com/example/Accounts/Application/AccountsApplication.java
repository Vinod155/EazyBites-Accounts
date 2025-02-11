package com.example.Accounts.Application;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info=@Info(
				title = "Accounts microservice REST API documentation",
				description="Vinnie Bank Accounts microservice",
				version = "v1",
				contact = @Contact(
						name="Vinnie",
						email="vin@gmail.com",
						url="vinnie.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "vin.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Vin Bank Account microservice details",
				url="vinnie.com"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
