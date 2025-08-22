package com.re_teraction.backend.presentation.enrollment;

import com.re_teraction.backend.application.enrollment.dto.CreateEnrollmentCommand;
import com.re_teraction.backend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;

public sealed interface EnrollmentApiDocs permits EnrollmentController {

    @Operation(summary = "수강 신청", description = "새로운 수강 신청을 생성하는 API")
    ResponseEntity<? extends ApiResponse<?>> enroll(
            @RequestBody(
                    description = "수강 신청에 필요한 정보",
                    required = true
            )
            CreateEnrollmentCommand cmd
    );

    @Operation(summary = "모든 수강 신청 조회", description = "등록된 모든 수강 신청을 조회하는 API")
    ResponseEntity<? extends ApiResponse<?>> getAll(
            @Parameter(hidden = true) Long userId
    );
}
