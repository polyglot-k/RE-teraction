package com.re_teraction.backend.application.project.dto;

import com.re_teraction.backend.domain.project.entity.ProjectJpaEntity;
import com.re_teraction.backend.domain.project.vo.ProjectCategory;
import java.util.Set;

public record ProjectResponse(
        Long id,
        String thumbnailUrl,
        String title,
        Set<ProjectCategory> categories
) {

    public static ProjectResponse from(ProjectJpaEntity project) {
        return new ProjectResponse(
                project.getId(),
                project.getThumbnailUrl(),
                project.getTitle(),
                project.getCategories()
        );
    }
}
