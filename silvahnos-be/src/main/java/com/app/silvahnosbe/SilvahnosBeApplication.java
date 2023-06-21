package com.app.silvahnosbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import java.util.TimeZone;

@SpringBootApplication
@EnableScheduling
public class SilvahnosBeApplication {

	public static void main(String[] args) {
		//TimeZone.setDefault(TimeZone.getTimeZone("America/Santigo"));
		SpringApplication.run(SilvahnosBeApplication.class, args);
	}
}
