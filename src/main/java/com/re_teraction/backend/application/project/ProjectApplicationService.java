package com.re_teraction.backend.application.project;

import com.re_teraction.backend.application.project.dto.CreateProjectCommand;
import com.re_teraction.backend.application.project.dto.ProjectResponse;
import com.re_teraction.backend.domain.project.entity.ProjectJpaEntity;
import com.re_teraction.backend.domain.project.repo.ProjectJpaRepository;
import com.re_teraction.backend.domain.project.service.ProjectMapper;
import com.re_teraction.backend.domain.project.vo.ProjectCategory;
import com.re_teraction.backend.global.annotation.ApplicationService;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
public class ProjectApplicationService {

    private final ProjectCategoryEventPublisher categoryEventPublisher;

    private final ProjectJpaRepository projectJpaRepository;
    private final ProjectMapper projectMapper;

    @Transactional
    public void createProject(Long userId, CreateProjectCommand cmd) {
        Long projectId = projectJpaRepository.save(toEntity(userId, cmd)).getId();

        // 카테고리 생성
        categoryEventPublisher.publishProjectCategoryCreated(
                projectId,
                Set.copyOf(cmd.categories())
        );
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<ProjectResponse> getProjects() {
        return projectJpaRepository.findAllWithThumbnail()
                .stream()
                .map(ProjectResponse::from)
                .toList();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<ProjectResponse> getProjectByCategory(String category) {
        ProjectCategory projectCategory = ProjectCategory.fromValue(category);
        return projectJpaRepository.findAllWithThumbnailByCategoriesContaining(projectCategory)
                .stream()
                .map(ProjectResponse::from)
                .toList();
    }

    private ProjectJpaEntity toEntity(Long userId, CreateProjectCommand cmd) {
        return ProjectJpaEntity.of(cmd.thumbnailId(), cmd.title(), userId);
    }

    public void deleteProject(Long projectId) {
        projectJpaRepository.deleteById(projectId);
    }
}
