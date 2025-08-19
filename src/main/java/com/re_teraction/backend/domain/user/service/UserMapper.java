package com.re_teraction.backend.domain.user.service;

import com.re_teraction.backend.domain.user.vo.Email;
import com.re_teraction.backend.domain.user.vo.LoginId;
import com.re_teraction.backend.domain.user.vo.Password;
import com.re_teraction.backend.domain.user.vo.PhoneNumber;
import com.re_teraction.backend.global.annotation.DomainService;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordDomainService passwordDomainService;

    public LoginId toLoginId(String loginId) {
        return LoginId.of(loginId);
    }

    public Email toEmail(String email) {
        return Email.of(email);
    }

    public Password toPassword(String password) {
        String encodedPassword = passwordDomainService.encode(password);
        return Password.of(encodedPassword);
    }

    public PhoneNumber toPhoneNumber(String phone) {
        return PhoneNumber.of(phone);
    }

}
