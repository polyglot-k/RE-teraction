package com.re_teraction.backend.application.enrollment;

import com.re_teraction.backend.application.enrollment.dto.CreateEnrollmentCommand;
import com.re_teraction.backend.application.enrollment.dto.EnrollmentResponse;
import com.re_teraction.backend.domain.base.Email;
import com.re_teraction.backend.domain.base.PhoneNumber;
import com.re_teraction.backend.domain.enrollment.Applicant;
import com.re_teraction.backend.domain.enrollment.EnrollmentJpaEntity;
import com.re_teraction.backend.domain.enrollment.EnrollmentJpaRepository;
import com.re_teraction.backend.domain.enrollment.Program;
import com.re_teraction.backend.global.annotation.ApplicationService;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class EnrollmentApplicationService {

    private final EnrollmentJpaRepository enrollmentJpaRepository;

    public EnrollmentResponse enroll(CreateEnrollmentCommand cmd) {
        EnrollmentJpaEntity enrollment = enrollmentJpaRepository.save(toEntity(cmd));
        return EnrollmentResponse.from(enrollment);
    }

    private EnrollmentJpaEntity toEntity(CreateEnrollmentCommand cmd) {
        Applicant applicant = Applicant.of(
                cmd.userName(),
                cmd.organization(),
                Email.of(cmd.email()),
                PhoneNumber.of(cmd.phoneNumber())
        );
        return EnrollmentJpaEntity.builder()
                .applicant(applicant)
                .program(Program.fromValue(cmd.program()))
                .build();
    }
}
