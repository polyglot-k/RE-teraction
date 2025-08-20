package com.re_teraction.backend.domain.enrollment.repository;

import com.re_teraction.backend.domain.enrollment.entity.EnrollmentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentJpaRepository extends JpaRepository<EnrollmentJpaEntity, Long> {

}
