package com.re_teraction.backend.domain.user.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.re_teraction.backend.infra.serializer.LoginIdSerializer;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonSerialize(using = LoginIdSerializer.class)
@ToString
public class LoginId {

    @Column(name = "login_id", nullable = false)
    private String value;

    private LoginId(String value){
        this.value = value;
    }
    public static LoginId of(String value) {
        return new LoginId(value);
    }

}
