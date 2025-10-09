package com.goutamthakur.flight.auth.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Profile("dev")
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI flightAuthOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Flight Auth Service APIs")
                        .description("APIs documentation for flight authentication microservice")
                        .contact(
                                new Contact()
                                        .name("Goutam Thakur")
                                        .url("https://goutamthakur.com")
                        )
                )
                .servers(
                        List.of(
                                new Server()
                                        .url("http://localhost:8080")
                                        .description("Local Development Server")
                        )
                );
    }

    @Bean public GroupedOpenApi v1Api(){
        return GroupedOpenApi.builder()
                .group("v1")
                .pathsToMatch("/api/v1/**")
                .build();
    }

    @Bean public GroupedOpenApi v2Api(){
        return GroupedOpenApi.builder()
                .group("v2")
                .pathsToMatch("/api/v2/**")
                .build();
    }
}
