package com.goutamthakur.flight.auth.api.v1.controller;

import com.goutamthakur.flight.auth.api.v1.dto.*;
import com.goutamthakur.flight.auth.application.AuthService;
import com.goutamthakur.flight.auth.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor()
public class AuthController {

  private final AuthService authService;

  @PostMapping("/signup")
  public ResponseEntity<ApiResponse<String>> signUp(
      @Valid @RequestBody SignUpRequest signUpRequest) {
    String result = authService.signUp(signUpRequest.getEmail(), signUpRequest.getPassword());
    return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(result, null));
  }

  @PostMapping("/otp/verify")
  public ResponseEntity<ApiResponse<VerifyOtpResponseDto>> verifyOtp(
      @Valid @RequestBody VerifyOtpRequestDto verifyOtpRequest) {
    VerifyOtpResponseDto result = authService.verifyOtp(verifyOtpRequest);
    return ResponseEntity.status(HttpStatus.OK)
        .body(ApiResponse.success("OTP verified successfully", result));
  }

  @PostMapping("/otp/resend")
  public ResponseEntity<ApiResponse<String>> resendOtp(
      @Valid @RequestBody ResendOtpRequestDto resendOtpRequest) {
    String result = authService.resendOtp(resendOtpRequest);
    return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(result, null));
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto loginRequest) {
    String result = authService.login(loginRequest);
    return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(result, null));
  }

  @PostMapping("/token/refresh")
  public ResponseEntity<ApiResponse<RefreshTokenResponseDto>> refreshToken(
      @Valid @RequestBody RefreshTokenRequestDto request) {
    RefreshTokenResponseDto response = authService.refreshToken(request);
    return ResponseEntity.status(HttpStatus.OK)
        .body(ApiResponse.success("Successfully issued new access token", response));
  }

  @PostMapping("/session/validate")
  public ResponseEntity<ApiResponse<String>> validateSession(
      @Valid @RequestBody ValidateSessionRequestDto requestDto) {
    String response = authService.validateUserSession(requestDto);
    return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(response, null));
  }

  @PostMapping("/logout")
  public ResponseEntity<ApiResponse<String>> logout() {
    return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("Login"));
  }
}
