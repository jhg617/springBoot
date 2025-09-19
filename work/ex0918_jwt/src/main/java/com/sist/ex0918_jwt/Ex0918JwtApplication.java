package com.sist.ex0918_jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Ex0918JwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ex0918JwtApplication.class, args);
	}

}
