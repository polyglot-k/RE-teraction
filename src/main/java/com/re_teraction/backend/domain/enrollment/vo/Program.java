package com.re_teraction.backend.domain.enrollment.vo;

import com.re_teraction.backend.global.exception.BusinessException;
import com.re_teraction.backend.global.exception.ErrorCode;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Program {
    SCHOLASTIC("Scholastic"),
    INDUSTRY("Industry");

    private static final Map<String, Program> BY_VALUE = new HashMap<>();

    static {
        for (Program e : values()) {
            BY_VALUE.put(e.value, e);
        }
    }

    private final String value;

    public static Program fromValue(String value) {
        Program program = BY_VALUE.get(value);
        if (program == null) {
            throw new BusinessException(ErrorCode.INVALID_PROGRAM);
        }
        return program;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
