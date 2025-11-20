package com.goutamthakur.flight.auth.infrastructure.persistence.adapter;

import com.goutamthakur.flight.auth.domain.enums.AuthType;
import com.goutamthakur.flight.auth.domain.model.User;
import com.goutamthakur.flight.auth.domain.port.UserRepositoryPort;
import com.goutamthakur.flight.auth.infrastructure.persistence.entity.RoleEntity;
import com.goutamthakur.flight.auth.infrastructure.persistence.entity.UserEntity;
import com.goutamthakur.flight.auth.infrastructure.persistence.jpa.UserJpaRepository;
import com.goutamthakur.flight.auth.infrastructure.persistence.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<User> findByEmail(String email){
            return userJpaRepository.findByEmailIsDeletedFalse(email)
                    .map(userMapper::toDomain);
    }

    @Override
    public User createUser(String email, String passwordHash){
        RoleEntity role = new RoleEntity();
        role.setId(2L);

        UserEntity user = userJpaRepository.save(
                UserEntity.builder()
                        .uuid(UUID.randomUUID().toString())
                        .roleId(role)
                        .email(email)
                        .passwordHash(passwordHash)
                        .authType(AuthType.PASSWORD)
                        .build()
        );
        return userMapper.toDomain(user);
    }
}
