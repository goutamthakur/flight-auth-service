package com.goutamthakur.flight.auth.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppException extends RuntimeException{
    private final HttpStatus statusCode;
    private final String errorCode;
    private final String description;

    public AppException(String message, HttpStatus statusCode, String errorCode, String description){
        super(message);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.description = description;
    }
    // TODO: set error code for app
    public AppException(String message, HttpStatus statusCode){
        this(message, statusCode, "ERROR", "No description provided");
    }

}
