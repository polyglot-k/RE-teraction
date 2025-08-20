package com.re_teraction.backend.presentation.project;

import com.re_teraction.backend.application.project.ProjectApplicationService;
import com.re_teraction.backend.application.project.dto.CreateProjectCommand;
import com.re_teraction.backend.application.project.dto.ProjectResponse;
import com.re_teraction.backend.global.response.ApiResponse;
import com.re_teraction.backend.global.response.ApiResponseFactory;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

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
    public ResponseEntity<? extends ApiResponse<?>> create(@RequestBody CreateProjectCommand cmd) {
        ProjectResponse response = projectApplicationService.createProject(1L, cmd);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(ApiResponseFactory.success(response, "프로젝트 생성 성공"));
    }
}
