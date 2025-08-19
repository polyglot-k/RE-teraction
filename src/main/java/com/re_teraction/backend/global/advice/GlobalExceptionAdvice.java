package com.re_teraction.backend.global.advice;

import com.re_teraction.backend.global.exception.BusinessException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessException(BusinessException e) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", e.getErrorCode().getStatus()); // 적절히 상태 코드 조정 가능
        body.put("error", e.getErrorCode().getMessage());
        body.put("code", e.getErrorCode().name());
        return ResponseEntity.badRequest().body(body);
    }
}
