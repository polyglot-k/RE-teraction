package com.re_teraction.backend.application.auth.dto;

public record LoginCommand(
        String loginId,
        String password
) {

}
