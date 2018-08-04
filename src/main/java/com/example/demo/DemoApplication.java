package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

@EntityScan({"com.example.Model"})
@EnableJpaRepositories(basePackages = {"com.example.Dao"})
@ComponentScan(basePackages = {"com.example.Dao", "com.example.Controller", "com.example.Model", "com.example.demo", "com.example.Model", "com.example.Service"})

public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
