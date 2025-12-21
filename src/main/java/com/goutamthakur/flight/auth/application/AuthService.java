package com.goutamthakur.flight.auth.application;

import com.goutamthakur.flight.auth.common.exception.AppException;
import com.goutamthakur.flight.auth.domain.enums.OtpPurpose;
import com.goutamthakur.flight.auth.domain.event.UserRegisteredEvent;
import com.goutamthakur.flight.auth.domain.model.User;
import com.goutamthakur.flight.auth.domain.port.OtpStorePort;
import com.goutamthakur.flight.auth.domain.port.UserEventPublisherPort;
import com.goutamthakur.flight.auth.domain.port.UserRepositoryPort;
import com.goutamthakur.flight.auth.domain.service.OtpCodeGenerator;
import com.goutamthakur.flight.auth.domain.service.PasswordHasher;
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

    // TODO: check if otp exists with email with purpose signup or login
//    public String verifyOtp(String email, String otp) {
//
//        User user = userRepositoryPort.findByEmailAndIsDeletedFalse(email)
//                .orElseThrow(() -> new AppException("Email not found", HttpStatus.BAD_REQUEST));
//
//        String otpKey = "registerOtp:" + email;
//
//        String storedOtp = otpStorePort.getOtp(otpKey);
//        if (storedOtp == null) {
//            throw new AppException("Invalid OTP", HttpStatus.BAD_REQUEST);
//        }
//
//        if (!storedOtp.equals(otp)) {
//            throw new AppException("Wrong OTP", HttpStatus.BAD_REQUEST);
//        }
//
//        otpStorePort.deleteOtp(otpKey);
//
//        // TODO: create a user session and change the response to user data with access and refresh token
//        return "Successfully OTP verified";
//    }
}
