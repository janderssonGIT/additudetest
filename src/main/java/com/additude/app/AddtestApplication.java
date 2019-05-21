package com.additude.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({ "com.additude.*" })
@EntityScan({"com.additude.*"})
@EnableJpaRepositories("com.additude.dao")
public class AddtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddtestApplication.class, args);
	}
}
