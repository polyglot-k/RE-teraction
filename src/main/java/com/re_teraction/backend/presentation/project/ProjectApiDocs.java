package com.re_teraction.backend.presentation.project;

import com.re_teraction.backend.application.project.dto.CreateProjectCommand;
import com.re_teraction.backend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;

public sealed interface ProjectApiDocs permits ProjectController {

    @Operation(summary = "모든 프로젝트 조회", description = "등록된 모든 프로젝트들을 조회하는 API")
    ResponseEntity<? extends ApiResponse<?>> getAllProjects();

    @Operation(summary = "카테고리별 프로젝트 조회", description = "특정 카테고리에 속하는 프로젝트들을 조회하는 API")
    ResponseEntity<? extends ApiResponse<?>> getProjectsByCategory(
            @Parameter(
                    name = "category",
                    in = ParameterIn.QUERY,
                    required = true,
                    description = "프로젝트 카테고리",
                    schema = @Schema(
                            type = "string",
                            allowableValues = {"Academia", "Scholastic", "Industry"}
                    )
            )
            String category
    );

    @Operation(summary = "프로젝트 생성", description = "사용자가 프로젝트를 생성하는 API")
    ResponseEntity<? extends ApiResponse<?>> create(
            @Parameter(hidden = true) Long userId,
            @RequestBody(
                    description = "프로젝트 생성에 필요한 정보",
                    required = true
            )
            CreateProjectCommand cmd
    );

    @Operation(summary = "프로젝트 삭제", description = "관리자가 특정 프로젝트를 삭제하는 API")
    ResponseEntity<? extends ApiResponse<?>> delete(
            @Parameter(hidden = true) Long userId,
            @Parameter(name = "projection 식별 Id")
            Long projectId
    );
}
