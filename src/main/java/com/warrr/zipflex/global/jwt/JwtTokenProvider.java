package com.warrr.zipflex.global.jwt;

import static com.warrr.zipflex.global.response.BaseResponseStatus.WRONG_JWT_TOKEN;
import static com.warrr.zipflex.api.auth.domain.model.TokenType.ACCESS_TOKEN;

import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import com.warrr.zipflex.api.auth.domain.model.TokenType;
import com.warrr.zipflex.global.exception.BaseException;
import com.warrr.zipflex.global.jwt.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private SecretKey secretKey;

    private SecretKey getSecretKey() {
        if (secretKey == null) {
            secretKey = Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes());
        }

        return secretKey;
    }

    public String generateToken(Authentication authentication, TokenType token) {
        Date now = new Date();
        Date expiration = new Date(now.getTime()
                        + (token == ACCESS_TOKEN ? jwtProperties.getAccessTokenExpireTime()
                                        : jwtProperties.getRefreshTokenExpireTime()));
        String roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(","));

        Claims claims = Jwts.claims().subject(authentication.getName()).add("roles", roles).build();
        
        return Jwts.builder()
                        .header().add("typ", "JWT").and()
                        .issuer(jwtProperties.getIssuer())
                        .issuedAt(now)
                        .expiration(expiration)
                        .claims(claims)
                        .signWith(getSecretKey())
                        .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token);
        String roles = claims.get("roles", String.class);
        Set<SimpleGrantedAuthority> authorities = Arrays.stream(roles.split(",")).map(String::trim)
                        .map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toSet());

        return new UsernamePasswordAuthenticationToken(
                        new User(claims.getSubject(), "", authorities), token, authorities);
    }

    public boolean isValidToken(String token) {
        try {
            parseClaims(token.trim());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isExpired(String token) {
        return parseClaims(token).getExpiration().before(new Date());
    }

    public String getUserUuidByToken(String token) {
        return parseClaims(token).getSubject();
    }

    private Claims parseClaims(String token) {
        try {
            if (token == null) {
                log.error("토큰이 존재하지 않습니다");
                throw new BaseException(WRONG_JWT_TOKEN);
            }

            return Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token)
                            .getPayload();

        } catch (ExpiredJwtException e) {
            log.error("만료된 토큰입니다");
            throw new BaseException(WRONG_JWT_TOKEN);
        } catch (UnsupportedJwtException e) {
            log.error("지원되지 않는 유형의 토큰입니다");
            throw new BaseException(WRONG_JWT_TOKEN);
        } catch (MalformedJwtException | IllegalArgumentException e) {
            log.error("잘못된 토큰입니다");
            throw new BaseException(WRONG_JWT_TOKEN);
        } catch (io.jsonwebtoken.security.SignatureException e) {
            log.error("SecretKey가 일치하지 않습니다");
            throw new BaseException(WRONG_JWT_TOKEN);
        } catch (Exception e) {
            log.error("토큰이 유효하지 않습니다");
            throw new BaseException(WRONG_JWT_TOKEN);
        }
    }
}
