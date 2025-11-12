package com.goutamthakur.flight.auth.application;

import com.goutamthakur.flight.auth.common.exception.AppException;
import com.goutamthakur.flight.auth.domain.model.User;
import com.goutamthakur.flight.auth.domain.port.OtpStorePort;
import com.goutamthakur.flight.auth.domain.port.UserRepositoryPort;
import com.goutamthakur.flight.auth.domain.service.OtpCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    // signup
    // Input validation if error return 400
    // Check if same email exist
    // Sign up with email and password
    // Generate 6 digit otp
    // Send an email for verification

    private final UserRepositoryPort userRepositoryPort;
    private final OtpStorePort otpStorePort;
    private final OtpCodeGenerator otpCodeGenerator;


    public String signUp(String email, String password){
        Optional<User> existingUser = userRepositoryPort.findByEmail(email);
        if(existingUser.isPresent()){
           throw new AppException("Email is already registered", HttpStatus.BAD_REQUEST);
        }
//        String passwordHash =
        // Password hashing logic pending
        User newUser = userRepositoryPort.createUser(email, password);

        String otp = otpCodeGenerator.generate(6);


        return "you bro" ;
    }

    // verify otp
    // input validation
    // check if email exists
    // check if otp exists with email with purpose signup or login
    //

}
