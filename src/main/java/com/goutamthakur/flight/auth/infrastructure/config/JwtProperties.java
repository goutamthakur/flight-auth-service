package com.goutamthakur.flight.auth.infrastructure.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
public class JwtProperties {

    @NotBlank
    private String secret;

    @Positive
    private long accessTokenExpiry;

    @Positive
    private long refreshTokenExpiry;
}
