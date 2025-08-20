package com.re_teraction.backend.presentation.enrollment;

import com.re_teraction.backend.application.enrollment.dto.CreateEnrollmentCommand;
import com.re_teraction.backend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public sealed interface EnrollmentApiDocs permits EnrollmentController {

    @Operation(summary = "수강 신청", description = "새로운 수강 신청을 생성하는 API")
    ResponseEntity<? extends ApiResponse<?>> enroll(
            @RequestBody
            CreateEnrollmentCommand cmd
    );
}
