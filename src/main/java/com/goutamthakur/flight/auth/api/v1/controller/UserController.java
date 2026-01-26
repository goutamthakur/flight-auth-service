package com.goutamthakur.flight.auth.api.v1.controller;

import com.goutamthakur.flight.auth.api.v1.dto.UserResponseDto;
import com.goutamthakur.flight.auth.application.UserService;
import com.goutamthakur.flight.auth.common.response.ApiResponse;
import com.goutamthakur.flight.auth.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  // Here X-User-Uuid is set by API Gateway
  @GetMapping("/me")
  public ResponseEntity<ApiResponse<UserResponseDto>> getUser(
      @RequestHeader("X-User-Uuid") String uuid) {
    User user = userService.getUserByUuid(uuid);
    return ResponseEntity.status(HttpStatus.OK)
        .body(ApiResponse.success(UserResponseDto.from(user)));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<UserResponseDto>> getUserById(@PathVariable Long id) {
    User user = userService.getUserById(id);
    return ResponseEntity.status(HttpStatus.OK)
        .body(ApiResponse.success(UserResponseDto.from(user)));
  }
}
