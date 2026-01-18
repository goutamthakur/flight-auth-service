package com.goutamthakur.flight.auth.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Getter
@Setter
@ToString
public class Session {
    private Long id;
    private Long userId;
    private String deviceId;
    private String deviceInfo;
    private String ipAddress;
    private String location;
    private String accessTokenJti;
    private String refreshTokenHash;
    private Instant refreshTokenExpiry;
    private Instant lastActiveAt;
    private Instant revokedAt;
    private String revokedReason;
    private boolean active = true;
    private Instant createdAt;
    private Instant updatedAt;
}

