package com.restapi.apirest;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApirestApplication {


	public static void main(String[] args) {
		SpringApplication.run(ApirestApplication.class, args);
	}

}
