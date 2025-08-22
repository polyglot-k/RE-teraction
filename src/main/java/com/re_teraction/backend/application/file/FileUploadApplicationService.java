package com.re_teraction.backend.application.file;

import com.re_teraction.backend.application.file.dto.FileResponse;
import com.re_teraction.backend.global.annotation.ApplicationService;
import com.re_teraction.backend.global.exception.BusinessException;
import com.re_teraction.backend.global.exception.ErrorCode;
import com.re_teraction.backend.infra.file.FileUploader;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@ApplicationService
@RequiredArgsConstructor
public class FileUploadApplicationService {

    private final FileUploader<MultipartFile> fileUploader;

    public FileResponse upload(MultipartFile file) {
        try {
            URI publicURI = fileUploader.upload(file);
            return FileResponse.of(publicURI);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.FILE_UPLOAD_FAILED);
        }
    }
}
