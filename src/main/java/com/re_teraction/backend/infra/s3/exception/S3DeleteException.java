package com.re_teraction.backend.infra.s3.exception;

public class S3DeleteException extends RuntimeException {

    public S3DeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}