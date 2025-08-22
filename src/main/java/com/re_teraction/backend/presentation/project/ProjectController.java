package com.re_teraction.backend.presentation.project;

import com.re_teraction.backend.application.project.ProjectApplicationService;
import com.re_teraction.backend.application.project.dto.CreateProjectCommand;
import com.re_teraction.backend.application.project.dto.ProjectResponse;
import com.re_teraction.backend.global.response.ApiResponse;
import com.re_teraction.backend.global.response.ApiResponseFactory;
import com.re_teraction.backend.global.security.resolver.AuthenticatedUserId;
import com.re_teraction.backend.global.security.resolver.AuthenticatedUserId.AccessType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public final class ProjectController implements ProjectApiDocs {

    private final ProjectApplicationService projectApplicationService;

    @GetMapping()
    public ResponseEntity<? extends ApiResponse<?>> getAllProjects() {
        List<ProjectResponse> responses = projectApplicationService.getProjects();
        return ResponseEntity
                .ok()
                .body(ApiResponseFactory.success(responses, "프로젝트 조회 성공"));
    }

    @GetMapping(params = "category")
    public ResponseEntity<? extends ApiResponse<?>> getProjectsByCategory(
            @RequestParam String category) {
        List<ProjectResponse> responses = projectApplicationService.getProjectByCategory(category);
        return ResponseEntity
                .ok()
                .body(ApiResponseFactory.success(responses, "카테고리별 프로젝트 조회 성공"));
    }

    @PostMapping()
    public ResponseEntity<? extends ApiResponse<?>> create(
            @AuthenticatedUserId Long userId,
            @RequestBody CreateProjectCommand cmd
    ) {
        projectApplicationService.createProject(userId, cmd);
        
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponseFactory.success("프로젝트 생성 성공"));
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<? extends ApiResponse<?>> delete(
            @AuthenticatedUserId(accessOnly = AccessType.ADMIN) Long userId,
            @PathVariable Long projectId
    ) {
        projectApplicationService.deleteProject(projectId);
        return ResponseEntity
                .ok()
                .body(ApiResponseFactory.success("프로젝트 삭제 성공"));
    }
}
