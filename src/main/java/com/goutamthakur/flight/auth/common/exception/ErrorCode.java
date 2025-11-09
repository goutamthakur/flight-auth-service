package com.goutamthakur.flight.auth.common.exception;

public enum ErrorCode {

    // Generic errors
    ERROR("No details provided"),
    INVALID_PARAMETER("Invalid parameters passed"),
    INVALID_JSON("Request body is malformed or contains invalid data."),
    VALIDATION_FAILED("Request failed due to validation error. Please check you inputs."),
    INTERNAL_ERROR("Internal server error. Please try again later.");

    // Constructor and getter method
    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
