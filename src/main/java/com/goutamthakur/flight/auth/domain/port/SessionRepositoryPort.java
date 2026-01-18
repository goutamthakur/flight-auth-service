package com.goutamthakur.flight.auth.domain.port;

import com.goutamthakur.flight.auth.domain.model.Session;

import java.time.Instant;

public interface SessionRepositoryPort {
    Session createSession(Session session);
    Session findByRefreshTokenHashAndIsActiveTrue(String refreshTokenHash);
    void updateAccessTokenJti(String refreshTokenHash, String newAccessTokenJti, Instant lastActiveAt);
}

