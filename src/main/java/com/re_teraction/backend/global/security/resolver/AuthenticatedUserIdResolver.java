package com.re_teraction.backend.global.security.resolver;

import com.re_teraction.backend.global.exception.UnauthorizedException;
import com.re_teraction.backend.global.security.core.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticatedUserIdResolver implements HandlerMethodArgumentResolver {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final int BEARER_TOKEN_PREFIX_LENGTH = 7;
    private final JwtTokenProvider tokenProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthenticatedUserId.class) &&
                parameter.getParameterType().equals(Long.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String token = extractToken(request);
        Claims claims = tokenProvider.getClaimsFromToken(token);
        Long userId = Long.valueOf(claims.getSubject());

        if (userId == null) {
            throw new UnauthorizedException("User ID not found in JWT token");
        }

        return userId;
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION_HEADER);
        if (header == null || !header.startsWith(BEARER_PREFIX)) {
            throw new UnauthorizedException("Authorization header missing or invalid");
        }
        String token = header.substring(BEARER_TOKEN_PREFIX_LENGTH);
        if (!tokenProvider.validateToken(token)) {
            throw new UnauthorizedException("Invalid or expired JWT token");
        }
        return token;
    }
}
