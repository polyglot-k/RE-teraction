package com.re_teraction.backend.presentation.user;

import com.re_teraction.backend.application.user.dto.CreateUserCommand;
import com.re_teraction.backend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;

public sealed interface UserApiDocs permits UserController {

    @Operation(summary = "사용자 조회", description = "사용자 ID로 특정 사용자를 조회하는 API")
    ResponseEntity<? extends ApiResponse<?>> getById(
            @Parameter(
                    name = "userId",
                    in = ParameterIn.PATH,
                    required = true,
                    description = "사용자 식별 ID"
            )
            Long userId
    );

    @Operation(summary = "사용자 생성", description = "새로운 사용자를 생성하는 API")
    ResponseEntity<? extends ApiResponse<?>> create(
            @RequestBody(
                    description = "사용자 생성에 필요한 정보",
                    required = true
            )
            CreateUserCommand cmd
    );
}
