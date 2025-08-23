package com.re_teraction.backend.application.project.dto;

import com.re_teraction.backend.domain.project.vo.ProjectCategory;
import com.re_teraction.backend.infra.persistence.dto.ProjectWithThumbnail;
import java.util.Set;

public record ProjectResponse(
        Long id,
        String title,
        String thumbnailUrl,
        String author,
        Set<ProjectCategory> categories
) {

    public static ProjectResponse from(ProjectWithThumbnail project) {
        return new ProjectResponse(
                project.id(),
                project.title(),
                project.thumbnailUrl(),
                project.author(),
                project.categories()
        );
    }
}
