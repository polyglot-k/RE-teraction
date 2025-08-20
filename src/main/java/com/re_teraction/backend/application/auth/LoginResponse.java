package com.re_teraction.backend.application.auth;

import com.re_teraction.backend.global.security.token.AccessToken;
import com.re_teraction.backend.global.security.token.RefreshToken;

public record LoginResponse (
        String accessToken,
        String refreshToken
) {

    public static LoginResponse of(AccessToken accessToken, RefreshToken refreshToken) {
        return new LoginResponse(accessToken.getValue(), refreshToken.getValue());
    }
}