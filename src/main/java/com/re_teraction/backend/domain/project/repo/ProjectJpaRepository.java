package com.re_teraction.backend.domain.project.repo;

import com.re_teraction.backend.domain.project.vo.ProjectCategory;
import com.re_teraction.backend.domain.project.entity.ProjectJpaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectJpaRepository extends JpaRepository<ProjectJpaEntity, Long> {
    List<ProjectJpaEntity> findByCategoriesContaining(ProjectCategory category);
}
