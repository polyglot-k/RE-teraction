package com.re_teraction.backend.application.auth;

import com.re_teraction.backend.application.auth.dto.LoginCommand;
import com.re_teraction.backend.application.user.UserApplicationService;
import com.re_teraction.backend.domain.user.entity.UserJpaEntity;
import com.re_teraction.backend.global.annotation.ApplicationService;
import com.re_teraction.backend.global.exception.BusinessException;
import com.re_teraction.backend.global.exception.ErrorCode;
import com.re_teraction.backend.global.security.core.JwtPayload;
import com.re_teraction.backend.global.security.core.JwtPayloadFactory;
import com.re_teraction.backend.global.security.core.JwtTokenFactory;
import com.re_teraction.backend.global.security.crypto.PasswordEncryptor;
import com.re_teraction.backend.global.security.token.AccessToken;
import com.re_teraction.backend.global.security.token.RefreshToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApplicationService
@RequiredArgsConstructor
@Slf4j
public class AuthApplicationService {

    private final UserApplicationService userApplicationService;
    private final JwtTokenFactory jwtTokenFactory;
    private final PasswordEncryptor passwordEncryptor;

    public LoginResponse login(LoginCommand cmd) {
        UserJpaEntity user = userApplicationService.getUserByLoginId(cmd.loginId());
        if (!passwordEncryptor.matches(cmd.password(), user.getPassword())) {
            throw new BusinessException(ErrorCode.INVALID_PASSWORD);
        }

        JwtPayload jwtPayload = JwtPayloadFactory.fromUser(user);

        AccessToken accessToken = jwtTokenFactory.createAccessToken(jwtPayload);
        RefreshToken refreshToken = jwtTokenFactory.createRefreshToken(jwtPayload);
        return LoginResponse.of(accessToken, refreshToken);
    }
}
