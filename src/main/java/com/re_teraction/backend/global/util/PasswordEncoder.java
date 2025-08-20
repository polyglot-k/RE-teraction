package com.re_teraction.backend.global.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@UtilityClass
public class PasswordEncoder {

    private static Pbkdf2PasswordEncoder ENCODER;

    public static void init(String secret, int iterations, int saltLength) {
        ENCODER = new Pbkdf2PasswordEncoder(
                secret,
                saltLength,
                iterations,
                Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256
        );
    }

    public static String encode(String plain) {
        if (ENCODER == null) {
            throw new IllegalStateException("PasswordEncoder not initialized");
        }
        return ENCODER.encode(plain);
    }

    public static boolean matches(String plain, String encoded) {
        if (ENCODER == null) {
            throw new IllegalStateException("PasswordEncoder not initialized");
        }
        return ENCODER.matches(plain, encoded);
    }
}
