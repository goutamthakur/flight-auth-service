package com.goutamthakur.flight.auth.api.v1.dto;

import com.goutamthakur.flight.auth.domain.enums.OtpPurpose;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class VerifyOtpRequestDto {
    @Email(message = "Email must be in valid format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotNull(message = "OTP purpose is required")
    private OtpPurpose purpose;

    @NotBlank(message = "OTP is required")
    private String otp;
}
