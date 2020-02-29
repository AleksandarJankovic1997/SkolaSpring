package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import com.example.demo.repository.UserRepository;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EntityScan("sql")
public class WebProjekatApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebProjekatApplication.class, args);
		
	}

}
