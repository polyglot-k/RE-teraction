package com.re_teraction.backend.domain.project.service;

import com.re_teraction.backend.domain.project.vo.ProjectCategory;
import com.re_teraction.backend.global.annotation.DomainService;
import java.util.Set;
import java.util.stream.Collectors;

@DomainService
public class ProjectMapper {

    public Set<ProjectCategory> toProjectCategories(Set<String> categories) {
        return categories.stream()
                .map(ProjectCategory::fromValue)
                .collect(Collectors.toSet());
    }
}
