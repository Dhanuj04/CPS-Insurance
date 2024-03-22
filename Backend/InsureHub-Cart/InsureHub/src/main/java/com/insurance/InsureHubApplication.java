package com.insurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@ComponentScan("insurance")
@EnableJpaRepositories("insurance.repository")
@EntityScan("insurance.entity")
@CrossOrigin
public class InsureHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsureHubApplication.class, args);
	}

	@Bean("webclient")
	public WebClient.Builder getWebClient() {
		return WebClient.builder();
	}
}