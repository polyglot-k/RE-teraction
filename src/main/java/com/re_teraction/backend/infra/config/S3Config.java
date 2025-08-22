package com.re_teraction.backend.infra.config;

import com.re_teraction.backend.infra.file.FileUploader;
import com.re_teraction.backend.infra.file.S3FileUploader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    @Value("${cloud.aws.s3.bucket-name}")
    private String bucketName;

    @Bean
    S3Client s3Client() {
        return S3Client.builder()
                .region(Region.AP_NORTHEAST_2)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }

    @Bean
    public FileUploader<MultipartFile> S3FileUploader(S3Client s3Client) {
        return new S3FileUploader(s3Client, bucketName);
    }
}
