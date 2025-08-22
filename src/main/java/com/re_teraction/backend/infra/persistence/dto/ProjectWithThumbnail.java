package com.re_teraction.backend.infra.persistence.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.re_teraction.backend.domain.project.vo.ProjectCategory;
import java.util.Set;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
public class ProjectWithThumbnail {

    public Long id;
    public String title;
    public String thumbnailUrl;
    public String author;
    public Set<ProjectCategory> categories;

    @QueryProjection
    public ProjectWithThumbnail(Long id, String title, String thumbnailUrl, String author,
            Set<ProjectCategory> categories) {
        this.id = id;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.author = author;
        this.categories = categories;
    }
}
