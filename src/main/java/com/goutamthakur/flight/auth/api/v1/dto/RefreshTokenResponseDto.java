package com.goutamthakur.flight.auth.api.v1.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RefreshTokenResponseDto {
    private String accessToken;
}
