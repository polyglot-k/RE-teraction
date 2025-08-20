package com.re_teraction.backend.global.security.core;

import io.jsonwebtoken.Claims;

public class DefaultJwtPayload extends JwtPayload {

    protected DefaultJwtPayload(String subject, Claims claims) {
        super(subject, claims);
    }

    @Override
    public Claims getClaims() {
        return claims;
    }

    @Override
    public String getSubject() {
        return subject;
    }
}
