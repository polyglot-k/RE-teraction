package com.re_teraction.backend.application.thumbnail.dto;

import java.net.URI;

public record ThumbnailResponse(Long ThumbnailId, URI uri) {

    public static ThumbnailResponse of(Long ThumbnailId, URI uri) {
        return new ThumbnailResponse(ThumbnailId, uri);
    }
}
