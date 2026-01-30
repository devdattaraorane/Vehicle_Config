package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages={"com.example.dtos","com.example.services","com.example.controllers","com.example.demo","com.example.auth"})
@EnableJpaRepositories("com.example.repositories")
@EntityScan("com.example.models") 
public class LoginProApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginProApplication.class, args);
	}

}
