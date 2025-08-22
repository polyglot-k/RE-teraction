package com.re_teraction.backend.application.paper.dto;

import com.re_teraction.backend.infra.persistence.dto.PaperWithThumbnail;

public record PaperWithThumbnailResponse(
        Long id,
        String author,
        String thumbnailUrl,
        String title
) {

    public static PaperWithThumbnailResponse from(PaperWithThumbnail paper) {
        return new PaperWithThumbnailResponse(
                paper.id(),
                paper.author(),
                paper.thumbnailPath(),
                paper.title()
        );
    }
}
