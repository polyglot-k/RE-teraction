package com.re_teraction.backend.presentation.paper;

import com.re_teraction.backend.application.paper.dto.CreatePaperCommand;
import com.re_teraction.backend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;

public sealed interface PaperApiDocs permits PaperController {

    @Operation(summary = "논문 조회", description = "등록된 모든 논문을 조회하는 API")
    ResponseEntity<? extends ApiResponse<?>> getAllPapers();

    @Operation(summary = "논문 등록", description = "새로운 논문을 등록하는 API")
    ResponseEntity<? extends ApiResponse<?>> create(
            @Parameter(hidden = true) Long userId,
            @RequestBody(
                    description = "논문 등록에 필요한 정보",
                    required = true
            )
            CreatePaperCommand cmd
    );

    @Operation(summary = "논문 삭제", description = "관리자가 특정 논문을 삭제하는 API")
    ResponseEntity<? extends ApiResponse<?>> delete(
            @Parameter(hidden = true) Long userId,
            @Parameter(name = "논문 등록 Id") Long paperId
    );
}
