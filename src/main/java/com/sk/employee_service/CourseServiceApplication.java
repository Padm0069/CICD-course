package com.sk.employee_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Course Microservice",
				description = "Course Microservice for managing courses and interacting with enrollment details.",
				summary = "Course CRUD Operations and integration with Enrollment Service",
				contact = @Contact(
						name = "Sumit Khandelwal",
						email = "synergy@test.com",
						url = "http://www.example.com"
				)
		)
)
@EnableFeignClients(basePackages = "com.sk.employee_service.service")  // Enable Feign clients for inter-service communication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class CourseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseServiceApplication.class, args);
	}

}
