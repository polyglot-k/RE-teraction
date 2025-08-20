package com.re_teraction.backend.global.security.core;

import com.re_teraction.backend.global.security.token.AccessToken;
import com.re_teraction.backend.global.security.token.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenFactory {

    private final JwtTokenProvider tokenProvider;

    @Value("${jwt.access-expiration}")
    private long accessExpiration;

    @Value("${jwt.refresh-expiration}")
    private long refreshExpiration;

    public AccessToken createAccessToken(JwtPayload payload) {
        String token = tokenProvider.generateToken(payload, accessExpiration);
        return new AccessToken(token, accessExpiration);
    }

    public RefreshToken createRefreshToken(JwtPayload payload) {
        String token = tokenProvider.generateToken(payload, refreshExpiration);
        return new RefreshToken(token, refreshExpiration);
    }
}