package com.goutamthakur.flight.auth.infrastructure.persistence.mapper;

import com.goutamthakur.flight.auth.domain.model.User;
import com.goutamthakur.flight.auth.infrastructure.persistence.entity.RoleEntity;
import com.goutamthakur.flight.auth.infrastructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toEntity(User user);
    User toDomain(UserEntity userEntity);

    // mapper to map roleId to RoleEntity
    default RoleEntity map(Long roleId){
        if(roleId == null){
            return null;
        }
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(roleId);
        return roleEntity;
    }

    // mapper to map RoleEntity to roleId
    default Long map(RoleEntity roleEntity){
        if(roleEntity == null){
            return null;
        }
        return roleEntity.getId();
    }
}
