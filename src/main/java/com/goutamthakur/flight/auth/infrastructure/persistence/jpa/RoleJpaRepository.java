package com.goutamthakur.flight.auth.infrastructure.persistence.jpa;

import com.goutamthakur.flight.auth.infrastructure.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoleJpaRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(String name);
}
