package com.re_teraction.backend.infra.config;

import com.re_teraction.backend.global.security.crypto.PasswordEncryptor;
import com.re_teraction.backend.infra.crypto.Pbkdf2PasswordEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncryptor passwordEncryptor(
            @Value("${security.password.salt}") String salt,
            @Value("${security.password.iterations}") int iterations,
            @Value("${security.password.salt-length}") int saltLength
    ) {
        Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder(
                salt,
                iterations,
                saltLength,
                Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA512
        );
        return new Pbkdf2PasswordEncryptor(encoder);
    }
}
