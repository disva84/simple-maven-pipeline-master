package com.example.myfirstmaven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyfirstmavenApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(MyfirstmavenApplication.class, args);
		System.out.println("Hello from Spring Boot in Docker!" );
		
		
	}

}
