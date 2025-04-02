package com.example.CreditScoreSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CreditScoreSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditScoreSystemApplication.class, args);
	}

}
