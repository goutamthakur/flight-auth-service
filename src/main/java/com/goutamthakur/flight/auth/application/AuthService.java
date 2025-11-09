package com.goutamthakur.flight.auth.application;

import org.springframework.stereotype.Service;

@Service
public class AuthService {
    // signup
    // Input validation if error return 400
    // Sign up with email and password
    // Check if same email exist
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
