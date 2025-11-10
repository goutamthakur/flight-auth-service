package com.goutamthakur.flight.auth.application;

import org.springframework.stereotype.Service;

@Service
public class AuthService {
    // signup
    // Input validation if error return 400
    // Check if same email exist
    // Sign up with email and password
    // Generate 6 digit otp
    // Send an email for verification

    public String signUp(String email, String password){
        return "Hello i am sign up";
    }

    // verify otp
    // input validation
    // check if email exists
    // check if otp exists with email with purpose signup or login
    //

}
