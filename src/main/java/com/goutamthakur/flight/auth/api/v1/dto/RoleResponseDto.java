package com.goutamthakur.flight.auth.api.v1.dto;

import com.goutamthakur.flight.auth.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RoleResponseDto {
    private Long id;
    private String name;
    private String description;

    public static RoleResponseDto from(Role role){
        return new RoleResponseDto(role.getId(), role.getName(), role.getDescription());
    }
}
