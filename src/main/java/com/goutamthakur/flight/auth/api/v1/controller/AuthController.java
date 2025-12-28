package com.goutamthakur.flight.auth.api.v1.controller;

import com.goutamthakur.flight.auth.api.v1.dto.SignUpRequest;
import com.goutamthakur.flight.auth.api.v1.dto.VerifyOtpRequestDto;
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
    public ResponseEntity<ApiResponse<String>> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        String result = authService.signUp(signUpRequest.getEmail(), signUpRequest.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(result, null));
    }

    @PostMapping("/otp/verify")
    public ResponseEntity<ApiResponse<String>> verifyOtp(@Valid @RequestBody VerifyOtpRequestDto verifyOtpRequest){
        String result = authService.verifyOtp(verifyOtpRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("OTP verified return user data, token"));
    }

    @PostMapping("/otp/resend")
    public ResponseEntity<ApiResponse<String>> resendOtp(){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("OTP resend successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("Login"));
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<ApiResponse<String>> refreshToken(){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("Login"));
    }

    @PostMapping("/session/validate")
    public ResponseEntity<ApiResponse<String>> validateUserSession(){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("Login"));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout(){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("Login"));
    }
}
