package com.goutamthakur.flight.auth.domain.port;

import com.goutamthakur.flight.auth.domain.model.User;

import java.util.Optional;

public interface UserRepositoryPort {
    Optional<User> checkEmailExists(String email);

}
