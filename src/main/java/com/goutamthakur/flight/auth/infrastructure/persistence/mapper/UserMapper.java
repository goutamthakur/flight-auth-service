package com.goutamthakur.flight.auth.infrastructure.persistence.mapper;

import com.goutamthakur.flight.auth.domain.model.User;
import com.goutamthakur.flight.auth.infrastructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toEntity(User user);
    User toDomain(UserEntity userEntity);
}
