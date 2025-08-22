package com.re_teraction.backend.application.file.dto;

import java.net.URI;

public record FileResponse(
        URI uri
) {

    public static FileResponse of(URI uri) {
        return new FileResponse(uri);
    }
}
