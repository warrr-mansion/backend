package com.warrr.zipflex.api.auth.service;

import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.warrr.zipflex.global.jwt.properties.JwtProperties;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final JwtProperties jwtProperties;

    private static final String TOKEN_PREFIX = "refreshToken:";

    @Override
    public void saveRefreshToken(String uuid, String refreshToken) {
        redisTemplate.opsForValue().set(TOKEN_PREFIX + uuid, refreshToken,
                        jwtProperties.getRefreshTokenExpireTime(), TimeUnit.MILLISECONDS);
    }

}
