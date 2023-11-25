package com.letsintern.letsintern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LetsinternApplication {

	public static void main(String[] args) {
		SpringApplication.run(LetsinternApplication.class, args);
	}

}
