package com.re_teraction.backend.domain.project.repo;

import com.re_teraction.backend.domain.project.entity.ProjectCategoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectCategoryJpaRepository extends
        JpaRepository<ProjectCategoryJpaEntity, Long> {

}
