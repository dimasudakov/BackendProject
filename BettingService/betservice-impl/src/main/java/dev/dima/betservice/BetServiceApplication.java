package dev.dima.betservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "dev.dima.betservice.repositories")
@OpenAPIDefinition
@EnableScheduling
public class BetServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BetServiceApplication.class, args);
    }

}
