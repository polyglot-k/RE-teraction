package com.re_teraction.backend.infra.s3;

import java.net.URI;
import org.springframework.web.multipart.MultipartFile;

public interface S3Service {

    boolean delete(String key);

    URI create(String key, MultipartFile file);
}
