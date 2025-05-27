package com.warrr.zipflex.global.support;

import java.time.Duration;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import com.warrr.zipflex.global.properties.JwtProperties;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CookieUtil {

    private final JwtProperties jwtProperties;
    
    public String createHttpOnlyCookie(String value, boolean isAccessToken) {
        String name = isAccessToken ? jwtProperties.getAccessTokenPrefix()
                        : jwtProperties.getRefreshTokenPrefix();
        long tokenExpireTime = isAccessToken ? jwtProperties.getAccessTokenExpireTime()
                        : jwtProperties.getRefreshTokenExpireTime();
        
        return ResponseCookie.from(name, value)
                .httpOnly(true)
                .path("/")
                .maxAge(Duration.ofSeconds(tokenExpireTime))
                .build().toString();
    }
}
