package com.goutamthakur.flight.auth.domain.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordHasher {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String hash(String password){
        return encoder.encode(password);
    }

    public Boolean compare(String rawPassword, String hashPassword){
        return encoder.matches(rawPassword, hashPassword);
    }
}
