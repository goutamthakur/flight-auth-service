package com.goutamthakur.flight.auth.common.exception;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException{
    private final int statusCode;
    private final String errorCode;
    private final String description;

    public AppException(String message, int statusCode, String errorCode, String description){
        super(message);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.description = description;
    }
    // TO DO: set error code for app
    public AppException(String message, int statusCode){
        this(message, statusCode, "ERROR", "No description provided");
    }

}
