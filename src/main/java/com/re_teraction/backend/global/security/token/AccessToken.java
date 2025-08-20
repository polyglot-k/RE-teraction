package com.re_teraction.backend.global.security.token;

public record AccessToken(String value, long expiresIn) implements Token {

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public long getExpiresIn() {
        return expiresIn;
    }
}