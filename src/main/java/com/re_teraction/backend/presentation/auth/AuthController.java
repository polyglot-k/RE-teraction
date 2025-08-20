package com.re_teraction.backend.presentation.auth;

import com.re_teraction.backend.application.auth.AuthApplicationService;
import com.re_teraction.backend.application.auth.LoginResponse;
import com.re_teraction.backend.application.auth.dto.LoginCommand;
import com.re_teraction.backend.global.response.ApiResponse;
import com.re_teraction.backend.global.response.ApiResponseFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public final class AuthController implements AuthApiDocs {

    private final AuthApplicationService authApplicationService;

    @PostMapping("/login")
    public ResponseEntity<? extends ApiResponse<?>> login(@RequestBody LoginCommand cmd) {
        LoginResponse response = authApplicationService.login(cmd);
        return ResponseEntity
                .ok()
                .body(ApiResponseFactory.success(response, "로그인 성공"));
    }
}
