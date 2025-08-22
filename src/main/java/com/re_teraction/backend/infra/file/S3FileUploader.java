package com.re_teraction.backend.infra.file;

import com.re_teraction.backend.infra.s3.S3Service;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class S3FileUploader implements FileUploader<MultipartFile> {

    private static final DateTimeFormatter PATH_DATE_FORMATTER = DateTimeFormatter.ofPattern(
            "yyyy/MM");
    private final S3Service s3Service;

    @Override
    public URI upload(String prefix, MultipartFile file) {
        validateFile(file);

        String key = generateS3Key(prefix, file);
        return s3Service.create(key, file);
    }

    private void validateFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("업로드할 파일이 존재하지 않습니다.");
        }
    }

    private String generateS3Key(String prefix, MultipartFile file) {
        String datePath = LocalDate.now().format(PATH_DATE_FORMATTER);
        String extension = getFileExtension(file);
        String uuid = UUID.randomUUID().toString();

        return String.format("%s/%s%s%s", datePath, prefix, uuid, extension);
    }

    private String getFileExtension(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.lastIndexOf('.') == -1) {
            return "";
        }
        return originalFilename.substring(originalFilename.lastIndexOf('.'));
    }
}
