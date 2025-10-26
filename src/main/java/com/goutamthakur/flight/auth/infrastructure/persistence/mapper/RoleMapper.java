package com.goutamthakur.flight.auth.infrastructure.persistence.mapper;

import com.goutamthakur.flight.auth.domain.model.Role;
import com.goutamthakur.flight.auth.infrastructure.persistence.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleEntity toEntity(Role role);
    Role toDomain(RoleEntity roleEntity);
}
