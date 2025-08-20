package com.re_teraction.backend.presentation.auth;

import com.re_teraction.backend.application.auth.dto.LoginCommand;
import com.re_teraction.backend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;

public sealed interface AuthApiDocs permits AuthController {

    @Operation(summary = "로그인", description = "사용자 로그인 API")
    ResponseEntity<? extends ApiResponse<?>> login(@RequestBody LoginCommand cmd);
}
