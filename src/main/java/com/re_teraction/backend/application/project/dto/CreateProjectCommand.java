package com.re_teraction.backend.application.project.dto;

import java.util.Set;

public record CreateProjectCommand(
        String thumbnailUrl,
        String title,
        Set<String> categories
) {

}
