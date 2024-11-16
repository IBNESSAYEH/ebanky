package com.YouCode.ebanky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EbankyApplication {

	public static void main(String[] args) {

		SpringApplication.run(EbankyApplication.class, args);
		System.out.println("aaaaaaaaaaaa");
	}

}
