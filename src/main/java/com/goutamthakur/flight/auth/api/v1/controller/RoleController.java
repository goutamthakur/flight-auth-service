package com.goutamthakur.flight.auth.api.v1.controller;

import com.goutamthakur.flight.auth.api.v1.dto.RoleResponseDto;
import com.goutamthakur.flight.auth.application.RoleService;
import com.goutamthakur.flight.auth.common.response.ApiResponse;
import com.goutamthakur.flight.auth.domain.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping()
    public ResponseEntity<ApiResponse<?>> fetchAllRoles(@RequestParam(value = "name", required = false) String name){
        List<RoleResponseDto> roles = roleService.getAllRoles(name)
                .stream()
                .map(RoleResponseDto::from)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(roleService.getAllRoles(name)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleResponseDto>> fetchRoleById(@PathVariable Long id) {
        Role role = roleService.getRoleById(id);
        return ResponseEntity.ok(ApiResponse.success(RoleResponseDto.from(role)));
    }

}
