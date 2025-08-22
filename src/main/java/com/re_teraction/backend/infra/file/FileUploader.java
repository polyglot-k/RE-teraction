package com.re_teraction.backend.infra.file;

import java.net.URI;

public interface FileUploader<T> {

    URI upload(String prefix, T target);
}
