package com.re_teraction.backend.presentation.file;

import com.re_teraction.backend.application.file.FileUploadApplicationService;
import com.re_teraction.backend.application.file.dto.FileResponse;
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
@RequestMapping("/api/v1/file-upload")
@Slf4j
@RequiredArgsConstructor
public final class FileUploadController implements FileUploadApiDocs {

    public final FileUploadApplicationService fileUploadApplicationService;

    @PostMapping(
            consumes = "multipart/form-data",
            produces = "application/json"
    )
    public ResponseEntity<? extends ApiResponse<?>> uploadUsingS3(
            @RequestPart("file") MultipartFile file
    ) {
        log.info("heelo");
        FileResponse response = fileUploadApplicationService.upload(file);
        return ResponseEntity
                .created(response.uri())
                .body(ApiResponseFactory.success(response, "파일 업로드 성공"));
    }
}
