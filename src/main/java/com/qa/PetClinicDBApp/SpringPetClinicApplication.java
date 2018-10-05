package com.qa.PetClinicDBApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringPetClinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringPetClinicApplication.class, args);
	}
}
