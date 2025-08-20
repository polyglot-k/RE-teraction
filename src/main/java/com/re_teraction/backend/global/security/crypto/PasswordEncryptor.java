package com.re_teraction.backend.global.security.crypto;

import com.re_teraction.backend.domain.user.vo.Password;

public interface PasswordEncryptor {

    Password encrypt(String rawPassword);

    boolean matches(String rawPassword, Password encryptedPassword);
}
