package com.re_teraction.backend.infra.s3;

import com.re_teraction.backend.infra.file.exception.S3UploadException;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Slf4j
public class S3ServiceImpl implements S3Service {

    private final S3Client s3Client;
    private final String bucketName;

    public S3ServiceImpl(S3Client s3Client, String bucketName) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
    }

    @PostConstruct
    void init() {
        log.info("S3Service initialized with bucket: {}", bucketName);
    }

    public boolean delete(String key) {
        try {
            DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();
            s3Client.deleteObject(deleteRequest);
            return true;
        } catch (Exception e) {
            log.error("Failed to delete S3 file: {}", key, e);
            return false;
        }
    }

    public URI create(String key, MultipartFile file) {
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .contentType(file.getContentType())
                    .build();

            s3Client.putObject(putObjectRequest,
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
            return buildPublicURI(key);
        } catch (IOException e) {
            throw new S3UploadException("S3 업로드에 실패했습니다: " + key, e);
        }
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
