package com.insurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
@ComponentScan("com.insurance")
@EnableJpaRepositories("com.insurance.repository")
@EntityScan("com.insurance.entity")
public class PolicyMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PolicyMsApplication.class, args);
	}

}