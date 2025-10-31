package com.example.incubyte_string_calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IncubyteStringCalculatorApplication {

	public static void main(String[] args) {
		// Start the Spring Boot application and get the context
		var context = SpringApplication.run(IncubyteStringCalculatorApplication.class, args);

		// Retrieve your service bean from the Spring context
		IncubyteStringCalculatorService calculator =
				context.getBean(IncubyteStringCalculatorService.class);
	}
}
