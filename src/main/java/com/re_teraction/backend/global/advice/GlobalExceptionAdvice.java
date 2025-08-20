package com.re_teraction.backend.global.advice;

import com.re_teraction.backend.global.exception.BusinessException;
import com.re_teraction.backend.global.exception.ErrorCode;
import com.re_teraction.backend.global.response.ApiResponse;
import com.re_teraction.backend.global.response.ApiResponseFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<? extends ApiResponse<?>> handleBusinessException(BusinessException e) {
        return buildErrorResponse(e.getErrorCode());
    }

    private ResponseEntity<? extends ApiResponse<?>> buildErrorResponse(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ApiResponseFactory.error(errorCode.getMessage()));
    }
}
