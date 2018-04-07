package com.example.weatherchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WeatherChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherChallengeApplication.class, args);
	}
}
