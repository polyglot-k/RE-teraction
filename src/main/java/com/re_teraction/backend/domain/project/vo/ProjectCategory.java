package com.re_teraction.backend.domain.project.vo;

import com.re_teraction.backend.global.exception.BusinessException;
import com.re_teraction.backend.global.exception.ErrorCode;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProjectCategory {
    ACADEMIA("Academia"),
    SCHOLASTIC("Scholastic"),
    INDUSTRY("Industry");

    private static final Map<String, ProjectCategory> BY_VALUE = new HashMap<>();
    private final String value;

    static {
        for (ProjectCategory e : values()) {
            BY_VALUE.put(e.value, e);
        }
    }
    public static ProjectCategory fromValue(String value) {
        ProjectCategory category = BY_VALUE.get(value);
        if (category == null) {
            throw new BusinessException(ErrorCode.INVALID_PROJECT_CATEGORY);
        }
        return category;
    }

    @Override
    public String toString() {
        return this.value;
    }
}