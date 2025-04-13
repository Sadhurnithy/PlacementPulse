package com.example.PlacementPulse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.PlacementPulse.repository")
public class PlacementPulseApplication {
	public static void main(String[] args) {
		SpringApplication.run(PlacementPulseApplication.class, args);
	}
}
