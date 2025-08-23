package com.re_teraction.backend.infra.persistence.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.re_teraction.backend.domain.project.vo.ProjectCategory;
import java.util.Set;

public record ProjectWithThumbnail(Long id, String title, String thumbnailUrl, String author,
                                   Set<ProjectCategory> categories) {

    @QueryProjection
    public ProjectWithThumbnail {
    }
}
