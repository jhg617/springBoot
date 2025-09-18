package com.sist.ex0918;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Ex0918Application {

	public static void main(String[] args) {
		SpringApplication.run(Ex0918Application.class, args);
	}

}
