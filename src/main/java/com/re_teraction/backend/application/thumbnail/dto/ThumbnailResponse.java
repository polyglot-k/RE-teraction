package com.re_teraction.backend.application.thumbnail.dto;

import java.net.URI;

public record ThumbnailResponse(Long thumbnailId, URI uri) {

    public static ThumbnailResponse of(Long thumbnailId, URI uri) {
        return new ThumbnailResponse(thumbnailId, uri);
    }
}
