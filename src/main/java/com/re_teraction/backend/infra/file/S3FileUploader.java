package com.re_teraction.backend.infra.file;

import com.re_teraction.backend.infra.file.exception.S3UploadException;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@RequiredArgsConstructor
public class S3FileUploader implements FileUploader<MultipartFile> {

    private static final DateTimeFormatter PATH_DATE_FORMATTER = DateTimeFormatter.ofPattern(
            "yyyy/MM/dd");
    private final S3Client s3Client;
    private final String bucketName;

    @Override
    public URI upload(MultipartFile file) {
        validateFile(file);

        String key = generateS3Key(file);

        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .contentType(file.getContentType())
                    .build();

            s3Client.putObject(putObjectRequest,
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

        } catch (IOException e) {
            throw new S3UploadException("S3 업로드에 실패했습니다: " + key, e);
        }

        return buildPublicURI(key);
    }

    private void validateFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("업로드할 파일이 존재하지 않습니다.");
        }
    }

    private String generateS3Key(MultipartFile file) {
        String datePath = LocalDate.now().format(PATH_DATE_FORMATTER);
        String extension = getFileExtension(file);
        String uuid = UUID.randomUUID().toString();

        return String.format("%s/%s%s", datePath, uuid, extension);
    }

    private String getFileExtension(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.lastIndexOf('.') == -1) {
            return "";
        }
        return originalFilename.substring(originalFilename.lastIndexOf('.'));
    }

    private URI buildPublicURI(String key) {
        return UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(bucketName + ".s3.amazonaws.com")
                .path(key)
                .build()
                .toUri();
    }
}
