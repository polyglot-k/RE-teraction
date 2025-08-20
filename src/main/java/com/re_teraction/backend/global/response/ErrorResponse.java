package com.re_teraction.backend.global.response;

import lombok.Getter;

@Getter
public class ErrorResponse extends ApiResponse<Void> {
    private final String errorCode;

    private ErrorResponse(String message, String errorCode) {
        super(false, message, null);
        this.errorCode = errorCode;
    }

    public static ErrorResponse of(String message, String errorCode) {
        return new ErrorResponse(message, errorCode);
    }
}