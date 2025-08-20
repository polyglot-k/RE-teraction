package com.re_teraction.backend.global.security.core;

import com.re_teraction.backend.global.exception.BusinessException;
import com.re_teraction.backend.global.exception.ErrorCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtTokenProvider {

    private final Key key;

    public JwtTokenProvider(
            @Value("${jwt.secret-key}") String secretKey
    ) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        this.key = io.jsonwebtoken.security.Keys.hmacShaKeyFor(keyBytes);
    }

    public <T extends JwtPayload> String generateToken(T payload, long expirationTime) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);
        if (payload == null || payload.getSubject() == null) {
            throw new BusinessException(ErrorCode.INVALID_JWT_PAYLOAD);
        }
        Claims claims = payload.getClaims();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new BusinessException(ErrorCode.EXPIRED_JWT_TOKEN);
        } catch (SignatureException e) {
            throw new BusinessException(ErrorCode.INVALID_JWT_SIGNATURE);
        } catch (IllegalArgumentException | JwtException e) {
            throw new BusinessException(ErrorCode.INVALID_JWT_TOKEN);
        }
    }
}
