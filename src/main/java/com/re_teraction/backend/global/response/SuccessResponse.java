package com.re_teraction.backend.global.response;

import lombok.Getter;

@Getter
public class SuccessResponse<T> extends ApiResponse<T> {

    private SuccessResponse(String message, T data) {
        super(true, message, data);
    }

    public static <T> SuccessResponse<T> of(String message, T data) {
        return new SuccessResponse<>(message, data);
    }

    public static SuccessResponse<Void> of(String message) {
        return new SuccessResponse<>(message, null);
    }
}
