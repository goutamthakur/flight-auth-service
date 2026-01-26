package com.goutamthakur.flight.auth.application;

import com.goutamthakur.flight.auth.domain.model.User;
import com.goutamthakur.flight.auth.domain.port.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepositoryPort userRepositoryPort;

    public User getUserByUuid(String uuid){
        return userRepositoryPort.findByUuidAndIsDeletedFalse(uuid);
    }

    public User getUserById(Long id){
        return userRepositoryPort.findByIdAndIsDeletedFalse(id);
    }

}
