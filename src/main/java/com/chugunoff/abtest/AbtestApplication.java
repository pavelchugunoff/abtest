package com.chugunoff.abtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AbtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbtestApplication.class, args);

	}

}
