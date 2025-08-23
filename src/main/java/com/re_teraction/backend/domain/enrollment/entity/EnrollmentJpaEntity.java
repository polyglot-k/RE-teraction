package com.re_teraction.backend.domain.enrollment.entity;

import com.re_teraction.backend.domain.enrollment.vo.Applicant;
import com.re_teraction.backend.domain.enrollment.vo.Program;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "enrollments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class EnrollmentJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Applicant applicant;

    @Enumerated(EnumType.STRING)
    private Program program;

    @Builder
    public EnrollmentJpaEntity(Applicant applicant, Program program) {
        this.applicant = applicant;
        this.program = program;
    }
}
