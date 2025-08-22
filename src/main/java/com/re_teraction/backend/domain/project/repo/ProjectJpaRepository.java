package com.re_teraction.backend.domain.project.repo;

import com.re_teraction.backend.domain.project.entity.ProjectJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectJpaRepository extends JpaRepository<ProjectJpaEntity, Long>,
        ProjectJpaRepositoryCustom {

}
