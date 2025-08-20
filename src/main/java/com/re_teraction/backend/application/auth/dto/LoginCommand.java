package com.re_teraction.backend.application.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginCommand(

        @Schema(description = "로그인할 아이디", example = "user01")
        String loginId,

        @Schema(description = "사용자 비밀번호", example = "1234")
        String password
) {

}
