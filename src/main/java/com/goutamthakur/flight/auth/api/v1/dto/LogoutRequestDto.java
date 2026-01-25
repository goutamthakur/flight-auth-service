package com.goutamthakur.flight.auth.api.v1.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LogoutRequestDto {
  @NotBlank(message = "Refresh token is required")
  private String refreshToken;
}
