package com.re_teraction.backend.global.response;

public class ApiResponseFactory {
    public static SuccessResponse<Void> success(String message) {
        return SuccessResponse.of(message);
    }

    public static <T> SuccessResponse<T> success(T data, String message) {
        return SuccessResponse.of(message,data);
    }

    public static ErrorResponse error(String message) {
        return ErrorResponse.of(message, null);
    }
}
