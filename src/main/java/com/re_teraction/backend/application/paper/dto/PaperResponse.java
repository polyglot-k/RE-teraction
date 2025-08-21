package com.re_teraction.backend.application.paper.dto;

import com.re_teraction.backend.domain.paper.PaperJpaEntity;

public record PaperResponse(
        Long id,
        String thumbnailUrl,
        String title
) {

    public static PaperResponse from(PaperJpaEntity paper) {
        return new PaperResponse(
                paper.getId(),
                paper.getThumbnailUrl(),
                paper.getTitle()
        );
    }
}
