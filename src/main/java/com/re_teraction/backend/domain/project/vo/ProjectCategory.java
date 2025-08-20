package com.re_teraction.backend.domain.project.vo;

import com.re_teraction.backend.global.exception.BusinessException;
import com.re_teraction.backend.global.exception.ErrorCode;
import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProjectCategory {
    ACADEMIA("Academia"),
    SCHOLASTIC("Scholastic"),
    INDUSTRY("Industry");

    private final String value;

    public static ProjectCategory fromValue(String value) {
        return Arrays.stream(ProjectCategory.values())
                .filter(category -> category.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_PROJECT_CATEGORY));
    }

    @Override
    public String toString() {
        return this.value;
    }
}