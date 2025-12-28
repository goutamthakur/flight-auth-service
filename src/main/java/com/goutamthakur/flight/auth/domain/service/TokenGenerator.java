package com.goutamthakur.flight.auth.domain.service;

import com.goutamthakur.flight.auth.domain.model.User;

public interface TokenGenerator {

    String generateAccessToken(User user);

    String generateRefreshToken(User user);

    boolean validateToken(String token);

    String extractUserUUID(String token);
}
