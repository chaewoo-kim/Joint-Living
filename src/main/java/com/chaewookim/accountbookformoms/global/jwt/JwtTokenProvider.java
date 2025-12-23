package com.chaewookim.accountbookformoms.global.jwt;

import com.chaewookim.accountbookformoms.domain.user.domain.CustomUserDetails;
import com.chaewookim.accountbookformoms.domain.user.domain.User;
import com.chaewookim.accountbookformoms.domain.user.domain.UserRole;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {

    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "Bearer";

    // 만료 시간 설정 (밀리초 단위)
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;            // 30분
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 14; // 14일

    private final Key key;

    // application.yml에서 secret 값 가져와서 키 생성
    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // 1. Access Token 생성
    public String createAccessToken(Authentication authentication) {
        long now = (new Date()).getTime();
        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(authentication.getName())                 // payload "sub": "username"
                .claim(AUTHORITIES_KEY, authorities) // payload "auth": "ROLE_USER"
                .setExpiration(accessTokenExpiresIn) // payload "exp": 1234567890 (유효기간)
                .signWith(key, SignatureAlgorithm.HS256) // header "alg": "HS256"
                .compact();
    }

    public String createRefreshToken(Authentication authentication) {
        long now = (new Date()).getTime();
        Date refreshTokenExpiresIn = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);

        return Jwts.builder()
                .setSubject(authentication.getName()) // ⭐️ 핵심: 이 토큰의 주인은 이 사람이다!
                .setExpiration(refreshTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 3. Refresh Token의 만료 시간 조회 (저장용)
    public Date getRefreshTokenExpireTime() {
        return new Date(new Date().getTime() + REFRESH_TOKEN_EXPIRE_TIME);
    }

    // 4. 토큰에서 인증 정보(Authentication) 꺼내기
    public Authentication getAuthentication(String accessToken) {
        // 토큰 복호화
        Claims claims = parseClaims(accessToken);

        String username = claims.getSubject();
        log.info("username = {}", username);
        String email = claims.get("email", String.class);
        log.info("email = {}", email);
        String role = claims.get("role", String.class);
        log.info("role = {}", role);

        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        // UserDetails 객체를 만들어서 Authentication 리턴
        UserDetails principal = new CustomUserDetails(
                User.builder()
                        .username(username)
                        .email(email)
                        .password("")
                        .build()
        );
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    // 5. 토큰 유효성 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

    // 토큰 파싱 (내부용)
    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }
}
