package com.goutamthakur.flight.auth.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppException extends RuntimeException{
    private final HttpStatus statusCode;
    private final ErrorCode errorCode;
    private final String description;

    public AppException(String message, HttpStatus statusCode, ErrorCode errorCode, String description){
        super(message);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.description = description;
    }

    public AppException(String message, HttpStatus statusCode){
        this(message, statusCode, ErrorCode.ERROR, ErrorCode.ERROR.getMessage());
    }

}
