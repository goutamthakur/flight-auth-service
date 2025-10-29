package com.goutamthakur.flight.auth.common.exception;

import com.goutamthakur.flight.auth.common.response.ApiResponse;
import com.goutamthakur.flight.auth.common.exception.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<Void>> handleAppException(AppException e){
        ErrorDetail error = new ErrorDetail(e.getStatusCode().value(), e.getErrorCode(), e.getDescription());
        return ResponseEntity.status(e.getStatusCode()).body(ApiResponse.error(e.getMessage(), error));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<Void>> handleTypeMismatch(MethodArgumentTypeMismatchException e){
        String paramName = e.getName();
        String requiredParamType = e.getRequiredType() != null ? e.getRequiredType().getSimpleName() : "unknown";
        ErrorDetail error = new ErrorDetail(HttpStatus.BAD_REQUEST.value(), "INVALID_PARAMETER", String.format("Parameter %s must is of invalid type", paramName));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error("Invalid parameter type", error));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidJson(HttpMessageNotReadableException e) {
        ErrorDetail error = new ErrorDetail(
                HttpStatus.BAD_REQUEST.value(),
                "INVALID_JSON",
                "Request body is malformed or contains invalid data."
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error("Malformed JSON request", error));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationErrors(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().getFirst().getDefaultMessage();
        ErrorDetail error = new ErrorDetail(
                HttpStatus.BAD_REQUEST.value(),
                "VALIDATION_FAILED",
                message
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error("Validation failed", error));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneralException(Exception e) {
        ErrorDetail error = new ErrorDetail(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL_ERROR",
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Something went wrong", error));
    }

}
