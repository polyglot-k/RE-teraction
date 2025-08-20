package com.re_teraction.backend.application.enrollment.dto;

import com.re_teraction.backend.domain.enrollment.EnrollmentJpaEntity;

public record EnrollmentResponse(
        Long id,
        String userName,
        String organization,
        String email,
        String phoneNumber,
        String program
) {

    public static EnrollmentResponse from(EnrollmentJpaEntity enrollment) {
        return new EnrollmentResponse(
                enrollment.getId(),
                enrollment.getApplicant().getName(),
                enrollment.getApplicant().getOrganization(),
                enrollment.getApplicant().getEmail().getValue(),
                enrollment.getApplicant().getPhoneNumber().getValue(),
                enrollment.getProgram().toString()
        );
    }
}
