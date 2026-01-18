package com.goutamthakur.flight.auth.domain.port;

import com.goutamthakur.flight.auth.domain.model.User;

import java.util.Optional;

public interface UserRepositoryPort {
    Optional<User> findByEmailAndIsDeletedFalse(String email);
    User createUser(String email, String passwordHash);
    User updateEmailVerified(Long userId, boolean emailVerified);
    User findByUuidAndIsDeletedFalse(String uuid);
}
