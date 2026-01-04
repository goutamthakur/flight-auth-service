package com.goutamthakur.flight.auth.application;

import com.goutamthakur.flight.auth.api.v1.dto.LoginRequestDto;
import com.goutamthakur.flight.auth.api.v1.dto.ResendOtpRequestDto;
import com.goutamthakur.flight.auth.api.v1.dto.VerifyOtpRequestDto;
import com.goutamthakur.flight.auth.api.v1.dto.VerifyOtpResponseDto;
import com.goutamthakur.flight.auth.common.exception.AppException;
import com.goutamthakur.flight.auth.domain.enums.OtpPurpose;
import com.goutamthakur.flight.auth.domain.event.UserLoginEvent;
import com.goutamthakur.flight.auth.domain.event.UserRegisteredEvent;
import com.goutamthakur.flight.auth.domain.model.User;
import com.goutamthakur.flight.auth.domain.model.Session;
import com.goutamthakur.flight.auth.domain.port.OtpStorePort;
import com.goutamthakur.flight.auth.domain.port.SessionRepositoryPort;
import com.goutamthakur.flight.auth.domain.port.UserEventPublisherPort;
import com.goutamthakur.flight.auth.domain.port.UserRepositoryPort;
import com.goutamthakur.flight.auth.domain.service.OtpCodeGenerator;
import com.goutamthakur.flight.auth.domain.service.Hasher;
import com.goutamthakur.flight.auth.domain.service.TokenGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepositoryPort userRepositoryPort;
    private final OtpStorePort otpStorePort;
    private final OtpCodeGenerator otpCodeGenerator;
    private final Hasher hasher;
    private final UserEventPublisherPort userEventPublisherPort;
    private final TokenGenerator tokenGenerator;
    private final SessionRepositoryPort sessionRepositoryPort;

    @Transactional
    public String signUp(String email, String password){
        Optional<User> existingUser = userRepositoryPort.findByEmailAndIsDeletedFalse(email);
        if(existingUser.isPresent()){
           throw new AppException("Email is already registered", HttpStatus.BAD_REQUEST);
        }
        String passwordHash = hasher.hash(password);
        User newUser = userRepositoryPort.createUser(email, passwordHash);
        String otp = otpCodeGenerator.generate(6);
        otpStorePort.saveOtp(OtpPurpose.SIGNUP, email, otp, 300);
        UserRegisteredEvent event = new UserRegisteredEvent(newUser.getEmail(), otp);
        userEventPublisherPort.publishUserRegisteredEvent(event);
        return "Successfully registered user and OTP send to email";
    }

    @Transactional
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

        if(request.getPurpose() == OtpPurpose.SIGNUP ||
                (request.getPurpose() == OtpPurpose.LOGIN && !user.isEmailVerified()))
        {
            user = userRepositoryPort.updateEmailVerified(user.getId(), true);
        }

        String accessToken = tokenGenerator.generateAccessToken(user);
        String refreshToken = tokenGenerator.generateRefreshToken(user);

        // Create user session
        String accessTokenJti = tokenGenerator.extractJti(accessToken);
        String refreshTokenHash = hasher.hashToken(refreshToken);
        Instant refreshTokenExpiry = tokenGenerator.extractExpiry(refreshToken);
        Instant lastActiveAt = Instant.now();

        Session session = new Session();
        session.setUserId(user.getId());
        session.setAccessTokenJti(accessTokenJti);
        session.setRefreshTokenHash(refreshTokenHash);
        session.setRefreshTokenExpiry(refreshTokenExpiry);
        session.setLastActiveAt(lastActiveAt);
        session.setActive(true);
        sessionRepositoryPort.createSession(session);

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

        if(request.getPurpose() == OtpPurpose.SIGNUP){
            UserRegisteredEvent event = new UserRegisteredEvent(user.getEmail(), otp);
            userEventPublisherPort.publishUserRegisteredEvent(event);
        } else if(request.getPurpose() == OtpPurpose.LOGIN){
            UserLoginEvent event = new UserLoginEvent(user.getEmail(), otp);
            userEventPublisherPort.publishUserLoginEvent(event);
        }

        return "OTP has been resent successfully";
    }

    public String login(LoginRequestDto request) {
        User user = userRepositoryPort.findByEmailAndIsDeletedFalse(request.getEmail())
                .orElseThrow(() -> new AppException("Invalid email or password", HttpStatus.UNAUTHORIZED));

        if (!user.isActive()) {
            throw new AppException("Account is inactive", HttpStatus.FORBIDDEN);
        }

        if (!hasher.compare(request.getPassword(), user.getPasswordHash())) {
            throw new AppException("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }

        // If email is not verified, send OTP with LOGIN purpose
        if (!user.isEmailVerified()) {
            otpStorePort.deleteOtp(OtpPurpose.LOGIN, user.getEmail());
            String otp = otpCodeGenerator.generate(6);
            otpStorePort.saveOtp(OtpPurpose.LOGIN, user.getEmail(), otp, 300);
            UserLoginEvent event = new UserLoginEvent(user.getEmail(), otp);
            userEventPublisherPort.publishUserLoginEvent(event);
            return "OTP sent to email for login verification";
        }

        // send UserLoginEvent
        String otp = otpCodeGenerator.generate(6);
        UserLoginEvent loginEvent = new UserLoginEvent(user.getEmail(), otp);
        userEventPublisherPort.publishUserLoginEvent(loginEvent);

        return "OTP sent to email for login verification";
    }
}
