package com.re_teraction.backend.infra.crypto;

import com.re_teraction.backend.domain.user.vo.Password;
import com.re_teraction.backend.global.security.crypto.PasswordEncryptor;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

public class Pbkdf2PasswordEncryptor implements PasswordEncryptor {

    private final Pbkdf2PasswordEncoder passwordEncoder;

    public Pbkdf2PasswordEncryptor(Pbkdf2PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Password encrypt(String rawPassword) {
        String encrypted = passwordEncoder.encode(rawPassword);
        return Password.of(encrypted);
    }

    @Override
    public boolean matches(String rawPassword, Password encryptedPassword) {
        return passwordEncoder.matches(rawPassword, encryptedPassword.getValue());
    }
}
