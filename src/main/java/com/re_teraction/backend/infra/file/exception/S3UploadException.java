package com.re_teraction.backend.infra.file.exception;

public class S3UploadException extends RuntimeException {

    public S3UploadException(String message, Throwable cause) {
        super(message, cause);
    }
}