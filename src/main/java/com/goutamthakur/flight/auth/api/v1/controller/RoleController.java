package com.goutamthakur.flight.auth.api.v1.controller;

import com.goutamthakur.flight.auth.api.v1.dto.RoleResponseDto;
import com.goutamthakur.flight.auth.application.RoleService;
import com.goutamthakur.flight.auth.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
