package com.app.silvahnosbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SilvahnosBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SilvahnosBeApplication.class, args);
	}
}
