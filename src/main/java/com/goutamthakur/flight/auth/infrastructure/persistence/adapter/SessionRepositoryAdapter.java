package com.goutamthakur.flight.auth.infrastructure.persistence.adapter;

import com.goutamthakur.flight.auth.common.exception.AppException;
import com.goutamthakur.flight.auth.domain.model.Session;
import com.goutamthakur.flight.auth.domain.port.SessionRepositoryPort;
import com.goutamthakur.flight.auth.infrastructure.persistence.entity.SessionEntity;
import com.goutamthakur.flight.auth.infrastructure.persistence.entity.UserEntity;
import com.goutamthakur.flight.auth.infrastructure.persistence.jpa.SessionJpaRepository;
import com.goutamthakur.flight.auth.infrastructure.persistence.jpa.UserJpaRepository;
import com.goutamthakur.flight.auth.infrastructure.persistence.mapper.SessionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SessionRepositoryAdapter implements SessionRepositoryPort {

    private final SessionJpaRepository sessionJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final SessionMapper sessionMapper;

    @Override
    public Session createSession(Session session) {
        UserEntity userEntity = userJpaRepository.findByIdAndIsDeletedFalse(session.getUserId())
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
        
        SessionEntity entity = sessionMapper.toEntity(session, userEntity);
        SessionEntity savedEntity = sessionJpaRepository.save(entity);
        return sessionMapper.toDomain(savedEntity);
    }
}

