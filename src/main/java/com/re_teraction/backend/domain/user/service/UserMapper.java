package com.re_teraction.backend.domain.user.service;

import com.re_teraction.backend.domain.user.vo.Email;
import com.re_teraction.backend.domain.user.vo.LoginId;
import com.re_teraction.backend.domain.user.vo.Password;
import com.re_teraction.backend.domain.user.vo.PhoneNumber;
import com.re_teraction.backend.global.annotation.DomainService;
import com.re_teraction.backend.global.util.PasswordEncoder;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class UserMapper {

    public LoginId toLoginId(String loginId) {
        return LoginId.of(loginId);
    }

    public Email toEmail(String email) {
        return Email.of(email);
    }

    public Password toPassword(String password) {
        return Password.of(PasswordEncoder.encode(password));
    }

    public PhoneNumber toPhoneNumber(String phone) {
        return PhoneNumber.of(phone);
    }

}
