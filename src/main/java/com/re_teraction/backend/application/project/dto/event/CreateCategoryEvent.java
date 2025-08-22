package com.re_teraction.backend.application.project.dto.event;

import java.util.Set;

public record CreateCategoryEvent(
        Long projectId,
        Set<String> categories
) {

}
