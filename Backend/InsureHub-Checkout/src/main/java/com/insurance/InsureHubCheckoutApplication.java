package com.insurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@Configuration
@ComponentScan("com.insurance")
@EnableJpaRepositories(basePackages = {"com.insurance.repository"})
@EntityScan(basePackages ={"com.insurance.entity","com.insurance.model"})
//@OpenAPIDefinition(info=@Info(title="user",description="des",version=""))
@CrossOrigin
public class InsureHubCheckoutApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsureHubCheckoutApplication.class, args);
	}

	@Bean("webclient")
//	@LoadBalanced
	public WebClient.Builder getwebClient()
	{
		return WebClient.builder();
	}

}

