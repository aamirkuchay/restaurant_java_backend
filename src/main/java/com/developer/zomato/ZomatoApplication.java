package com.developer.zomato;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ZomatoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZomatoApplication.class, args);
	}

}
