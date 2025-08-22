package com.re_teraction.backend.application.project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Set;

public record CreateProjectCommand(

        @Schema(description = "프로젝트 썸네일 이미지 Id", example = "1")
        Long thumbnailId,

        @Schema(description = "프로젝트 제목", example = "RE-teraction")
        String title,

        @Schema(description = "프로젝트 카테고리 목록", example = "[\"Academia\", \"Scholastic\"]")
        Set<String> categories

) {

}
