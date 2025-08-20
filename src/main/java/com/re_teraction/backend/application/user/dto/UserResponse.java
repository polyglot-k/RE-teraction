package com.re_teraction.backend.application.user.dto;

import com.re_teraction.backend.domain.user.entity.UserJpaEntity;

public record UserResponse(
        Long id,
        String loginId,
        String name,
        String email,
        String phoneNumber
) {

    public static UserResponse from(UserJpaEntity userJpaEntity) {
        return new UserResponse(
                userJpaEntity.getId(),
                userJpaEntity.getLoginId().getValue(),
                userJpaEntity.getName(),
                userJpaEntity.getEmail().getValue(),
                userJpaEntity.getPhoneNumber().getValue()
        );
    }
}
