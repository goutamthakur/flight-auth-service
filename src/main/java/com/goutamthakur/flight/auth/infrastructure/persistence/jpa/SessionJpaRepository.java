package com.goutamthakur.flight.auth.infrastructure.persistence.jpa;

import com.goutamthakur.flight.auth.infrastructure.persistence.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionJpaRepository extends JpaRepository<SessionEntity, Long> {
}

