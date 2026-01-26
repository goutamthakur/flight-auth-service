package com.goutamthakur.flight.auth.infrastructure.persistence.adapter;

import com.goutamthakur.flight.auth.common.exception.AppException;
import com.goutamthakur.flight.auth.domain.enums.AuthType;
import com.goutamthakur.flight.auth.domain.model.User;
import com.goutamthakur.flight.auth.domain.port.UserRepositoryPort;
import com.goutamthakur.flight.auth.infrastructure.persistence.entity.RoleEntity;
import com.goutamthakur.flight.auth.infrastructure.persistence.entity.UserEntity;
import com.goutamthakur.flight.auth.infrastructure.persistence.jpa.UserJpaRepository;
import com.goutamthakur.flight.auth.infrastructure.persistence.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

  private final UserJpaRepository userJpaRepository;
  private final UserMapper userMapper;

  @Override
  public Optional<User> findByEmailAndIsDeletedFalse(String email) {
    return userJpaRepository.findByEmailAndIsDeletedFalse(email).map(userMapper::toDomain);
  }

  @Override
  public User createUser(String email, String passwordHash) {
    RoleEntity role = new RoleEntity();
    role.setId(2L);

    UserEntity user =
        userJpaRepository.save(
            UserEntity.builder()
                .uuid(UUID.randomUUID().toString())
                .roleId(role)
                .email(email)
                .passwordHash(passwordHash)
                .authType(AuthType.PASSWORD)
                .isActive(true)
                .build());
    return userMapper.toDomain(user);
  }

  @Override
  public User updateEmailVerified(Long userId, boolean emailVerified) {
    UserEntity userEntity =
        userJpaRepository
            .findById(userId)
            .orElseThrow(
                () -> new AppException("User not found with id: " + userId, HttpStatus.NOT_FOUND));
    userEntity.setEmailVerified(emailVerified);
    UserEntity updatedUser = userJpaRepository.save(userEntity);
    return userMapper.toDomain(updatedUser);
  }

  @Override
  public User findByUuidAndIsDeletedFalse(String uuid) {
    UserEntity userEntity =
        userJpaRepository
            .findByUuidAndIsDeletedFalse(uuid)
            .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
    return userMapper.toDomain(userEntity);
  }

  @Override
  public User findByIdAndIsDeletedFalse(Long userId) {
    UserEntity userEntity =
        userJpaRepository
            .findByIdAndIsDeletedFalse(userId)
            .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
    return userMapper.toDomain(userEntity);
  }
}
