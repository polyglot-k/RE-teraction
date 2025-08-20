package com.re_teraction.backend.domain.user.entity;

import com.re_teraction.backend.domain.base.Email;
import com.re_teraction.backend.domain.base.PhoneNumber;
import com.re_teraction.backend.domain.user.vo.LoginId;
import com.re_teraction.backend.domain.user.vo.Password;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_user_email", columnNames = "email"),
                @UniqueConstraint(name = "uk_user_login_id", columnNames = "login_id"),
                @UniqueConstraint(name = "uk_user_phone_number", columnNames = "phone_number")
        })
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class UserJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    private LoginId loginId;

    @Embedded
    private Password password;

    @Embedded
    private Email email;

    @Embedded
    private PhoneNumber phoneNumber;

    @Builder
    public UserJpaEntity(
            String name,
            LoginId loginId,
            Password password,
            Email email,
            PhoneNumber phoneNumber
    ) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public static UserJpaEntity of(LoginId loginId, Password password, String name, Email email,
            PhoneNumber phoneNumber) {
        return new UserJpaEntity(name, loginId, password, email, phoneNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserJpaEntity that)) {
            return false;
        }
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
