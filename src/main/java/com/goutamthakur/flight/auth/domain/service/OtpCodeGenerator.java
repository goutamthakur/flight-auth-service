package com.goutamthakur.flight.auth.domain.service;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class OtpCodeGenerator {

    private static final SecureRandom secureRandom = new SecureRandom();

    public String generate(int digits){
        return String.format("%0" + digits + "d", secureRandom.nextInt((int)Math.pow(10, digits)));
    }
}
