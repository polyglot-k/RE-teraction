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

    private final ProjectJpaRepository projectJpaRepository;
    private final ProjectMapper projectMapper;

    @Transactional
    public ProjectResponse createProject(Long userId, CreateProjectCommand cmd) {
        ProjectJpaEntity project = projectJpaRepository.save(toEntity(userId, cmd));
        return ProjectResponse.from(project);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<ProjectResponse> getProjects() {
        return projectJpaRepository.findAll()
                .stream()
                .map(ProjectResponse::from)
                .toList();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<ProjectResponse> getProjectByCategory(String category) {
        return projectJpaRepository.findByCategoriesContaining(ProjectCategory.fromValue(category))
                .stream()
                .map(ProjectResponse::from)
                .toList();
    }

    private ProjectJpaEntity toEntity(Long userId, CreateProjectCommand cmd) {
        Set<ProjectCategory> categories = projectMapper.toProjectCategories(cmd.categories());
        return ProjectJpaEntity.of(cmd.thumbnailUrl(), cmd.title(), categories, userId);
    }

    public void deleteProject(Long projectId) {
        projectJpaRepository.deleteById(projectId);
    }
}
