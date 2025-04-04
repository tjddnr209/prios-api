package com.furyjoker.priosapi.global.jwt;

import com.furyjoker.priosapi.auth.dto.TokenDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;

    private Key key;

    private final long ACCESS_EXP = 1000L * 60 * 60;
    private final long REFRESH_EXP = 1000L * 60 * 60 * 24;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public TokenDto generateTokenDto(Long memberId) {
        return TokenDto.builder()
                .accessToken(createToken(memberId, ACCESS_EXP))
                .refreshToken(createToken(memberId, REFRESH_EXP))
                .build();
    }

    public String createToken(Long memberId, long exp) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(String.valueOf(memberId))
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + exp))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Long getMemberId(String token) {
        return Long.valueOf(
                Jwts.parserBuilder().setSigningKey(key).build()
                        .parseClaimsJws(token)
                        .getBody()
                        .getSubject()
        );
    }

    public TokenDto reissue(HttpServletRequest request) {
        String refresh = request.getHeader("Refresh");
        if (!validateToken(refresh)) {
            throw new RuntimeException("유효하지 않은 리프레시 토큰입니다.");
        }
        Long memberId = getMemberId(refresh);
        return generateTokenDto(memberId);
    }
}
