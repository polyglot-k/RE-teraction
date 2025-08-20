package com.re_teraction.backend.global.security.core;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public abstract class JwtPayload {

    protected final String subject;
    protected final Claims claims;

    abstract Claims getClaims();

    abstract String getSubject();
}
