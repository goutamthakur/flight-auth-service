package com.goutamthakur.flight.auth.common.response;

import com.goutamthakur.flight.auth.common.exception.ErrorDetail;
import lombok.Getter;

import java.time.Instant;

@Getter
public class ApiResponse<T> {
    private final boolean success;
    private final String message;
    private final T data;
    private final ErrorDetail error;
    private final Instant timestamp;

    public ApiResponse(boolean success, String message, T data, ErrorDetail error){
        this.success = success;
        this.message = message;
        this.data = data;
        this.error = error;
        this.timestamp = Instant.now();
    }

    public static <T> ApiResponse<T> success(String message, T data){
        return new ApiResponse<>(true, message, data, null);
    }

    public static <T> ApiResponse<T> success(T data){
        return new ApiResponse<>(true, "Request completed successfully", data, null);
    }

    public static <T> ApiResponse<T> error(String message, ErrorDetail error){
        return new ApiResponse<>(false, message, null, error);
    }

    public static <T> ApiResponse<T> error(ErrorDetail error){
        return new ApiResponse<>(false, "Something went wrong", null, error);
    }

}
