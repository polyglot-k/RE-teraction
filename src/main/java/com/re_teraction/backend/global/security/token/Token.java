package com.re_teraction.backend.global.security.token;

public sealed interface Token permits AccessToken, RefreshToken {
    String getValue();
    long getExpiresIn();
}