package com.re_teraction.backend.domain.user.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.re_teraction.backend.global.util.PasswordEncoder;
import com.re_teraction.backend.infra.serializer.PasswordSerializer;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonSerialize(using = PasswordSerializer.class)
@ToString
public class Password {
    @Column(name = "password", nullable = false)
    private String value;

    private Password(String value) {
        this.value = value;
    }
    public static Password of(String value) {
        return new Password(value);
    }
    public boolean matches(String rawPassword) {
        return PasswordEncoder.matches(rawPassword, this.value);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Password password)) return false;
        return Objects.equals(value, password.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
