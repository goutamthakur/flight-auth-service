package com.goutamthakur.flight.auth.api.v1.controller;

import com.goutamthakur.flight.auth.api.v1.dto.SignUpRequest;
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

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<String>> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(authService.signUp(signUpRequest.getEmail(), signUpRequest.getPassword())));
    }

    @PostMapping("/otp/verify")
    public ResponseEntity<ApiResponse<String>> verifyOtp(){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("OTP verified return user data, token"));
    }

    @PostMapping("/otp/send")
    public ResponseEntity<ApiResponse<String>> resendOtp(){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("OTP send successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("Login"));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout(){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("Login"));
    }



}
