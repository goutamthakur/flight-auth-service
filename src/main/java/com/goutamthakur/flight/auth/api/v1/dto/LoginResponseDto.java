package com.goutamthakur.flight.auth.api.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDto {
    private Long userId;
    private String userUuid;
    private Long roleId;
    private String accessToken;
    private String refreshToken;
}

