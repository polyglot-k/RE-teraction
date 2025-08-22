package com.re_teraction.backend.application.thumbnail;

import com.re_teraction.backend.application.thumbnail.dto.ThumbnailResponse;
import com.re_teraction.backend.domain.thumbnail.ThumbnailJpaEntity;
import com.re_teraction.backend.domain.thumbnail.ThumbnailJpaRepository;
import com.re_teraction.backend.global.annotation.ApplicationService;
import com.re_teraction.backend.global.exception.BusinessException;
import com.re_teraction.backend.global.exception.ErrorCode;
import com.re_teraction.backend.global.util.URIPathUtils;
import com.re_teraction.backend.global.util.URIPathUtils.URIPath;
import com.re_teraction.backend.infra.file.FileUploader;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@ApplicationService
@RequiredArgsConstructor
public class ThumbnailUploadApplicationService {

    private final ThumbnailJpaRepository thumbnailJpaRepository;

    private final FileUploader<MultipartFile> fileUploader;

    public ThumbnailResponse upload(MultipartFile file, String prefix) {
        try {
            URI publicURI = fileUploader.upload(prefix, file);
            Long thumbnailId = saveThumbnail(publicURI);
            return ThumbnailResponse.of(thumbnailId, publicURI);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.FILE_UPLOAD_FAILED);
        }
    }

    public Long saveThumbnail(URI publicURI) {
        URIPath path = URIPathUtils.extract(publicURI);
        ThumbnailJpaEntity thumbnail = ThumbnailJpaEntity.create(
                path.directory(),
                path.filename()
        );
        return thumbnailJpaRepository.save(thumbnail).getId();
    }
}
