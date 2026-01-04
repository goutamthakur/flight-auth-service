package com.goutamthakur.flight.auth.infrastructure.persistence.mapper;

import com.goutamthakur.flight.auth.domain.model.Session;
import com.goutamthakur.flight.auth.infrastructure.persistence.entity.SessionEntity;
import com.goutamthakur.flight.auth.infrastructure.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class SessionMapper {

    public Session toDomain(SessionEntity entity) {
        if (entity == null) {
            return null;
        }
        Session session = new Session();
        session.setId(entity.getId());
        session.setUserId(entity.getUser() != null ? entity.getUser().getId() : null);
        session.setDeviceId(entity.getDeviceId());
        session.setDeviceInfo(entity.getDeviceInfo());
        session.setIpAddress(entity.getIpAddress());
        session.setLocation(entity.getLocation());
        session.setAccessTokenJti(entity.getAccessTokenJti());
        session.setRefreshTokenHash(entity.getRefreshTokenHash());
        session.setRefreshTokenExpiry(entity.getRefreshTokenExpiry());
        session.setLastActiveAt(entity.getLastActiveAt());
        session.setRevokedAt(entity.getRevokedAt());
        session.setRevokedReason(entity.getRevokedReason());
        session.setActive(entity.isActive());
        session.setCreatedAt(entity.getCreatedAt());
        session.setUpdatedAt(entity.getUpdatedAt());
        return session;
    }

    public SessionEntity toEntity(Session session, UserEntity userEntity) {
        if (session == null) {
            return null;
        }
        return SessionEntity.builder()
                .id(session.getId())
                .user(userEntity)
                .deviceId(session.getDeviceId())
                .deviceInfo(session.getDeviceInfo())
                .ipAddress(session.getIpAddress())
                .location(session.getLocation())
                .accessTokenJti(session.getAccessTokenJti())
                .refreshTokenHash(session.getRefreshTokenHash())
                .refreshTokenExpiry(session.getRefreshTokenExpiry())
                .lastActiveAt(session.getLastActiveAt())
                .revokedAt(session.getRevokedAt())
                .revokedReason(session.getRevokedReason())
                .isActive(session.isActive())
                .createdAt(session.getCreatedAt())
                .updatedAt(session.getUpdatedAt())
                .build();
    }
}

