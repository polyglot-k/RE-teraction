package com.re_teraction.backend.application.project;

import com.re_teraction.backend.application.project.dto.event.CreateCategoryEvent;
import com.re_teraction.backend.domain.project.entity.ProjectCategoryJpaEntity;
import com.re_teraction.backend.domain.project.repo.ProjectCategoryJpaRepository;
import com.re_teraction.backend.domain.project.vo.ProjectCategory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectCategoryEventHandler {

    private final ProjectCategoryJpaRepository projectCategoryJpaRepository;

    @EventListener(
            classes = CreateCategoryEvent.class,
            condition = "#event.categories != null and #event.categories.size() > 0"
    )
    @Async
    public void saveCategory(CreateCategoryEvent event) {
        Set<ProjectCategory> categories = toProjectCategories(event.categories());
        List<ProjectCategoryJpaEntity> categoryEntities = categories.stream()
                .map(category -> ProjectCategoryJpaEntity.create(event.projectId(), category))
                .toList();

        projectCategoryJpaRepository.saveAll(categoryEntities);
    }

    private Set<ProjectCategory> toProjectCategories(Set<String> categories) {
        return categories.stream()
                .map(ProjectCategory::fromValue)
                .collect(Collectors.toSet());
    }
}
