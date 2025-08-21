package com.re_teraction.backend.application.paper.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreatePaperCommand(

        @Schema(description = "논문 썸네일 이미지 URL", example = "https://example.com/image.png")
        String thumbnailUrl,

        @Schema(description = "논문 제목", example = "RE-teraction")
        String title
) {

}
