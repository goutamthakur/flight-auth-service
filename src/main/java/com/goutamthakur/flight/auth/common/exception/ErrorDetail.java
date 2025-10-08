package com.goutamthakur.flight.auth.common.exception;

public class ErrorDetail {
    private int statusCode;
    private String errorCode;
    private String description;

    public ErrorDetail(int statusCode, String errorCode, String description) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.description = description;
    }
}
