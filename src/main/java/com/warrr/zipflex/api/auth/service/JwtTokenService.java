package com.warrr.zipflex.api.auth.service;

public interface JwtTokenService {

    void saveRefreshToken(String uuid, String refreshToken);
    
}
