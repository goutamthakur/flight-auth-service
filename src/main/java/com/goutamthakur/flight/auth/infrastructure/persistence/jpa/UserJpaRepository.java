package com.goutamthakur.flight.auth.infrastructure.persistence.jpa;

import com.goutamthakur.flight.auth.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmailAndIsDeletedFalse(String email);
    Optional<UserEntity> findByIdAndIsDeletedFalse(Long id);
    Optional<UserEntity> findByUuidAndIsDeletedFalse(String uuid);
}
