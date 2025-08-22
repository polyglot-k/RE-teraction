package com.re_teraction.backend.presentation.enrollment;

import com.re_teraction.backend.application.enrollment.EnrollmentApplicationService;
import com.re_teraction.backend.application.enrollment.dto.CreateEnrollmentCommand;
import com.re_teraction.backend.application.enrollment.dto.EnrollmentResponse;
import com.re_teraction.backend.global.response.ApiResponse;
import com.re_teraction.backend.global.response.ApiResponseFactory;
import com.re_teraction.backend.global.security.resolver.AuthenticatedUserId;
import com.re_teraction.backend.global.security.resolver.AuthenticatedUserId.AccessType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/enrollments")
@RequiredArgsConstructor
public final class EnrollmentController implements EnrollmentApiDocs {

    private final EnrollmentApplicationService enrollmentApplicationService;

    @PostMapping()
    public ResponseEntity<? extends ApiResponse<?>> enroll(
            @RequestBody CreateEnrollmentCommand cmd
    ) {
        EnrollmentResponse response = enrollmentApplicationService.enroll(cmd);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponseFactory.success(response, "수강 신청 성공"));
    }

    @GetMapping()
    public ResponseEntity<? extends ApiResponse<?>> getAll(
            @AuthenticatedUserId(accessOnly = AccessType.ADMIN) Long userId
    ) {
        List<EnrollmentResponse> responses = enrollmentApplicationService.getAllEnrollments();
        return ResponseEntity
                .ok()
                .body(ApiResponseFactory.success(responses, "모든 수강 신청 조회 성공"));
    }
}
