package com.goutamthakur.flight.auth.api.v1.controller;

import com.goutamthakur.flight.auth.common.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<String>> signUp() {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Sign up successful"));
    }

    @PostMapping("/otp/verify")
    public ResponseEntity<ApiResponse<String>> verifyOtp(){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("OTP verified return user data, token"));
    }

    @PostMapping("/otp/resend")
    public ResponseEntity<ApiResponse<String>> resendOtp(){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("OTP send successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("Login"));
    }



}
