package com.re_teraction.backend.presentation.thumbnail;

import com.re_teraction.backend.application.thumbnail.ThumbnailUploadApplicationService;
import com.re_teraction.backend.application.thumbnail.dto.ThumbnailResponse;
import com.re_teraction.backend.global.response.ApiResponse;
import com.re_teraction.backend.global.response.ApiResponseFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/thumbnail")
@Slf4j
@RequiredArgsConstructor
public final class ThumbnailUploadController implements ThumbnailUploadApiDocs {

    public final ThumbnailUploadApplicationService thumbnailUploadApplicationService;

    @PostMapping(
            consumes = "multipart/form-data",
            produces = "application/json"
    )
    public ResponseEntity<? extends ApiResponse<?>> uploadUsingS3(
            @RequestPart("file") MultipartFile file,
            @RequestPart("prefix") String prefix
    ) {
        ThumbnailResponse response = thumbnailUploadApplicationService.upload(file, prefix);
        return ResponseEntity
                .created(response.uri())
                .body(ApiResponseFactory.success(response, "파일 업로드 성공"));
    }
}
