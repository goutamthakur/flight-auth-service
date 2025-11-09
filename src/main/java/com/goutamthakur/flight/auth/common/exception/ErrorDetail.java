package com.goutamthakur.flight.auth.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDetail {
    private int statusCode;
    private ErrorCode errorCode;
    private String description;
}
