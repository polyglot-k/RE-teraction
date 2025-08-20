package com.re_teraction.backend.global.security.core;

import com.re_teraction.backend.domain.user.entity.UserJpaEntity;
import com.re_teraction.backend.global.security.constant.JwtClaims;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtPayloadFactory {
    public static JwtPayload fromUser(UserJpaEntity user) {
        Claims claims = Jwts.claims();
        claims.put(JwtClaims.EMAIL, user.getEmail().getValue());
        return new DefaultJwtPayload(String.valueOf(user.getId()), claims);
    }
}
