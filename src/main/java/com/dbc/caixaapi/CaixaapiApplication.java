package com.dbc.caixaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CaixaapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaixaapiApplication.class, args);
	}

}
