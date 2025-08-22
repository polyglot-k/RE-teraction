package com.re_teraction.backend.presentation.thumbnail;

import com.re_teraction.backend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

public sealed interface ThumbnailUploadApiDocs permits ThumbnailUploadController {

    @Operation(
            summary = "파일 업로드",
            description = "S3에 파일을 업로드합니다."
    )
    ResponseEntity<? extends ApiResponse<?>> uploadUsingS3(
            @Parameter(
                    name = "file",
                    description = "업로드할 파일",
                    required = true,
                    content = @Content(
                            mediaType = "multipart/form-data"
                    )
            )
            MultipartFile file,
            @Parameter(
                    name = "prefix",
                    description = "파일 경로 접두사(paper, project 가 접두사로 들어갈 수 있다.)",
                    required = true,
                    content = @Content(
                            mediaType = "multipart/form-data"
                    )
            )
            @RequestPart("prefix") String prefix
    );
}
