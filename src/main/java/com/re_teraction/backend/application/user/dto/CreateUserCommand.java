package com.re_teraction.backend.application.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateUserCommand(

        @Schema(description = "로그인 아이디", example = "user01")
        String loginId,

        @Schema(description = "비밀번호", example = "password123!")
        String password,

        @Schema(description = "사용자 이름", example = "홍길동")
        String name,

        @Schema(description = "이메일 주소", example = "user01@example.com")
        String email,

        @Schema(description = "전화번호", example = "010-1234-5678")
        String phoneNumber

) {

}
