package com.re_teraction.backend.domain.project.repo;

import com.re_teraction.backend.domain.project.vo.ProjectCategory;
import com.re_teraction.backend.infra.persistence.dto.ProjectWithThumbnail;
import java.util.List;

public interface ProjectJpaRepositoryCustom {

    List<ProjectWithThumbnail> findAllWithThumbnail();

    List<ProjectWithThumbnail> findAllWithThumbnailByCategoriesContaining(
            ProjectCategory categoryParam);
}
