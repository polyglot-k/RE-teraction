package com.re_teraction.backend.application.enrollment.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateEnrollmentCommand(

        @Schema(description = "사용자 이름", example = "홍길동")
        String userName,

        @Schema(description = "조직 이름", example = "Re-Interaction")
        String organization,

        @Schema(description = "이메일 주소", example = "user01@example.com")
        String email,

        @Schema(description = "전화번호", example = "010-1234-5678")
        String phoneNumber,

        @Schema(description = "프로그램 이름", example = "Scholastic")
        String program

) {

}
