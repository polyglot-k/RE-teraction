package com.re_teraction.backend.domain.user.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.re_teraction.backend.global.exception.BusinessException;
import com.re_teraction.backend.global.exception.ErrorCode;
import com.re_teraction.backend.infra.serializer.PhoneNumberSerializer;
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
@JsonSerialize(using = PhoneNumberSerializer.class)
@ToString
public class PhoneNumber {

    private static final Pattern PHONE_NUMBER_REGEX =
            Pattern.compile("^010-\\d{4}-\\d{4}$");
    @Column(name = "phone_number", nullable = false)
    private String value;

    private PhoneNumber(String value) {
        if (!PHONE_NUMBER_REGEX.matcher(value).matches()) {
            throw new BusinessException(ErrorCode.INVALID_PHONE_NUMBER_FORMAT);
        }
        this.value = value;
    }

    public static PhoneNumber of(String value) {
        return new PhoneNumber(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PhoneNumber phoneNumber)) {
            return false;
        }
        return Objects.equals(value, phoneNumber.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
