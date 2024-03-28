package com.user.usermicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class UserMicroserviceApplication {

	public static void main(String[] args) {

		SpringApplication.run(UserMicroserviceApplication.class, args);
	}
@GetMapping("/")
		public String sayHello(){
	       return "hello from backend";
		}
}
