package com.goutamthakur.flight.auth.api.v1.controller;

import com.goutamthakur.flight.auth.api.v1.dto.RoleResponseDto;
import com.goutamthakur.flight.auth.application.RoleService;
import com.goutamthakur.flight.auth.common.response.ApiResponse;
import com.goutamthakur.flight.auth.domain.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping()
    public ResponseEntity<ApiResponse<List<RoleResponseDto>>> fetchAllRoles(){
        List<RoleResponseDto> roles = roleService
                .getAllRoles()
                .stream()
                .map(RoleResponseDto::from)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(roles));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleResponseDto>> fetchRoleById(@PathVariable Long id) {
        Role role = roleService.getRoleById(id);
        return ResponseEntity.ok(ApiResponse.success(RoleResponseDto.from(role)));
    }


    // TODO: Write all the pending get like get by id, get by name
    // TODO: Handle exception at application service level, set error codes, write a general error response
    //       for unhandled exception in GlobalExceptionHandler
    // TODO: AppException is causing some error solve it
    // TODO: Why does map struct give null value configure it so that every time it must give proper result
}
