package com.re_teraction.backend.application.user.dto;

public record CreateUserCommand(
        String loginId,
        String password,
        String name,
        String email,
        String phoneNumber
) {

}
