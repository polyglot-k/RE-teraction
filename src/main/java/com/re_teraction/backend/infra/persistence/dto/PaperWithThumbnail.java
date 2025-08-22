package com.re_teraction.backend.infra.persistence.dto;

public record PaperWithThumbnail(
        Long id,
        String title,
        String author,
        String thumbnailPath
) {

}
