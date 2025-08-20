package com.re_teraction.backend.global.response;

import com.re_teraction.backend.global.exception.ErrorCode;

public class ApiResponseFactory {
    public static SuccessResponse<Void> success(String message) {
        return SuccessResponse.of(message);
    }

    public static <T> SuccessResponse<T> success(T data, String message) {
        return SuccessResponse.of(message,data);
    }

    public static ErrorResponse error(ErrorCode errorCode) {
        return ErrorResponse.of(errorCode.getMessage(), errorCode.name());
    }
}
