package com.re_teraction.backend.global.runner;

import com.re_teraction.backend.global.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderInitializer implements CommandLineRunner {

    @Value("${security.password.salt}")
    private String salt;

    @Value("${security.password.iterations}")
    private int iterations;

    @Value("${security.password.salt-length}")
    private int saltLength;

    @Override
    public void run(String... args) {
        PasswordEncoder.init(salt, iterations, saltLength);
    }
}