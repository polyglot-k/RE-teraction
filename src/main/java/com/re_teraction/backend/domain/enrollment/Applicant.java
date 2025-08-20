package com.re_teraction.backend.domain.enrollment;

import com.re_teraction.backend.domain.base.Email;
import com.re_teraction.backend.domain.base.PhoneNumber;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Applicant {

    @Column(name = "applicant_name", nullable = false)
    private String name;

    @Column(name = "applicant_organization", nullable = false)
    private String organization;

    @Embedded
    private Email email;

    @Embedded
    private PhoneNumber phoneNumber;

    private Applicant(String name, String organization, Email email, PhoneNumber phoneNumber) {
        this.name = name;
        this.organization = organization;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public static Applicant of(String name, String organization, Email email,
            PhoneNumber phoneNumber) {
        return new Applicant(name, organization, email, phoneNumber);
    }
}
