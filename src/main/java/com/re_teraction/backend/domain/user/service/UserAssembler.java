package com.re_teraction.backend.domain.user.service;

import com.re_teraction.backend.domain.base.Email;
import com.re_teraction.backend.domain.base.PhoneNumber;
import com.re_teraction.backend.domain.user.vo.LoginId;
import com.re_teraction.backend.global.annotation.DomainService;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class UserAssembler {

    public LoginId toLoginId(String loginId) {
        return LoginId.of(loginId);
    }

    public Email toEmail(String email) {
        return Email.of(email);
    }

    public PhoneNumber toPhoneNumber(String phone) {
        return PhoneNumber.of(phone);
    }

}
