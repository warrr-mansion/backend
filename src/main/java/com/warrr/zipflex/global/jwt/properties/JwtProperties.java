package com.warrr.zipflex.global.jwt.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String issuer;
    private String secretKey;
    private long accessExpireTime;
    private long refreshExpireTime;
    private String tokenPrefix;
    private String accessTokenPrefix;
    private String refreshTokenPrefix;

}
