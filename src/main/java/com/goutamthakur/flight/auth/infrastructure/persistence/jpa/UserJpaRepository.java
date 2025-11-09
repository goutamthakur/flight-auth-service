package com.goutamthakur.flight.auth.infrastructure.persistence.jpa;

import com.goutamthakur.flight.auth.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
