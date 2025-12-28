package com.goutamthakur.flight.auth.application;

import com.goutamthakur.flight.auth.api.v1.dto.ResendOtpRequestDto;
import com.goutamthakur.flight.auth.api.v1.dto.VerifyOtpRequestDto;
import com.goutamthakur.flight.auth.api.v1.dto.VerifyOtpResponseDto;
import com.goutamthakur.flight.auth.common.exception.AppException;
import com.goutamthakur.flight.auth.domain.enums.OtpPurpose;
import com.goutamthakur.flight.auth.domain.event.UserRegisteredEvent;
import com.goutamthakur.flight.auth.domain.model.User;
import com.goutamthakur.flight.auth.domain.port.OtpStorePort;
import com.goutamthakur.flight.auth.domain.port.UserEventPublisherPort;
import com.goutamthakur.flight.auth.domain.port.UserRepositoryPort;
import com.goutamthakur.flight.auth.domain.service.OtpCodeGenerator;
import com.goutamthakur.flight.auth.domain.service.PasswordHasher;
import com.goutamthakur.flight.auth.domain.service.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepositoryPort userRepositoryPort;
    private final OtpStorePort otpStorePort;
    private final OtpCodeGenerator otpCodeGenerator;
    private final PasswordHasher passwordHasher;
    private final UserEventPublisherPort userEventPublisherPort;
    private final TokenGenerator tokenGenerator;

    public String signUp(String email, String password){
        Optional<User> existingUser = userRepositoryPort.findByEmailAndIsDeletedFalse(email);
        if(existingUser.isPresent()){
           throw new AppException("Email is already registered", HttpStatus.BAD_REQUEST);
        }
        String passwordHash = passwordHasher.hash(password);
        User newUser = userRepositoryPort.createUser(email, passwordHash);
        String otp = otpCodeGenerator.generate(6);
        otpStorePort.saveOtp(OtpPurpose.SIGNUP, email, otp, 300);
        UserRegisteredEvent event = new UserRegisteredEvent(newUser.getEmail(), otp);
        userEventPublisherPort.publishUserRegisteredEvent(event);
        return "Successfully registered user and OTP send to email";
    }

    public VerifyOtpResponseDto verifyOtp(VerifyOtpRequestDto request) {
        User user = userRepositoryPort.findByEmailAndIsDeletedFalse(request.getEmail())
                .orElseThrow(() -> new AppException("Email not found", HttpStatus.BAD_REQUEST));

        String storedOtp = otpStorePort.getOtp(request.getPurpose(), user.getEmail());
        if (storedOtp == null) {
            throw new AppException("Invalid OTP", HttpStatus.BAD_REQUEST);
        }
        if (!storedOtp.equals(request.getOtp())) {
            throw new AppException("Wrong OTP", HttpStatus.BAD_REQUEST);
        }
        otpStorePort.deleteOtp(request.getPurpose(), user.getEmail());

        if(request.getPurpose() == OtpPurpose.SIGNUP){
            user = userRepositoryPort.updateEmailVerified(user.getId(), true);
        }

        String accessToken = tokenGenerator.generateAccessToken(user);
        String refreshToken = tokenGenerator.generateRefreshToken(user);

        return new VerifyOtpResponseDto(
                user.getId(),
                user.getUuid(),
                user.getRoleId(),
                accessToken,
                refreshToken
        );
    }

    public String resendOtp(ResendOtpRequestDto request) {
        User user = userRepositoryPort.findByEmailAndIsDeletedFalse(request.getEmail())
                .orElseThrow(() -> new AppException("Email not found", HttpStatus.BAD_REQUEST));

        otpStorePort.deleteOtp(request.getPurpose(), user.getEmail());

        String otp = otpCodeGenerator.generate(6);

        otpStorePort.saveOtp(request.getPurpose(), user.getEmail(), otp, 300);
        
        // Send OTP to user via event currently assuming flow for signup
        UserRegisteredEvent event = new UserRegisteredEvent(user.getEmail(), otp);
        userEventPublisherPort.publishUserRegisteredEvent(event);
        
        return "OTP has been resent successfully";
    }
}
