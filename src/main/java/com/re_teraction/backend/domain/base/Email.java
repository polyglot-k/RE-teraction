package com.re_teraction.backend.domain.base;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.re_teraction.backend.global.exception.BusinessException;
import com.re_teraction.backend.global.exception.ErrorCode;
import com.re_teraction.backend.infra.serializer.EmailSerializer;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonSerialize(using = EmailSerializer.class)
@ToString
public class Email {

    private static final Pattern EMAIL_REGEX =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String value;

    private Email(String value) {
        if (!EMAIL_REGEX.matcher(value).matches()) {
            throw new BusinessException(ErrorCode.INVALID_EMAIL_FORMAT);
        }
        this.value = value;
    }

    public static Email of(String value) {
        return new Email(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Email email)) {
            return false;
        }
        return Objects.equals(value, email.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
