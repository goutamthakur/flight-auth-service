package com.goutamthakur.flight.auth.common.exception;

import com.goutamthakur.flight.auth.common.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<Void>> handleAppException(AppException e){
        ErrorDetail error = new ErrorDetail(e.getStatusCode(), e.getErrorCode(), e.getDescription());
        return ResponseEntity.status(e.getStatusCode()).body(ApiResponse.error(e.getMessage(), error));
    }

}
