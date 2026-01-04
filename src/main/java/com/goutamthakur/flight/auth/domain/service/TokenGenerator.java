package com.goutamthakur.flight.auth.domain.service;

import com.goutamthakur.flight.auth.domain.model.User;

import java.time.Instant;

public interface TokenGenerator {

    String generateAccessToken(User user);

    String generateRefreshToken(User user);

    boolean validateToken(String token);

    String extractUserUUID(String token);

    String extractJti(String token);

    Instant extractExpiry(String token);
}
