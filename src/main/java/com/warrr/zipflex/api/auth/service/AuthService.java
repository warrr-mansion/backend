package com.warrr.zipflex.api.auth.service;

import com.warrr.zipflex.api.auth.dto.in.SignInRequestDto;
import com.warrr.zipflex.api.auth.dto.out.JwtTokenResponseDto;
import com.warrr.zipflex.api.auth.vo.in.SignUpRequestVo;

public interface AuthService {
    
    void signUp(SignUpRequestVo requestVo);

    JwtTokenResponseDto signIn(SignInRequestDto requestDto);
    
}
