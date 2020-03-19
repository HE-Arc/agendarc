package com.hearc.agendarc;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AgendarcApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendarcApplication.class, args);
	}

	@PostConstruct
	public void init() {
		
	}

}
