package com.warrr.zipflex.api.auth.service;

import com.warrr.zipflex.api.auth.vo.in.SignUpRequestVo;

public interface AuthService {
    
    void signUp(SignUpRequestVo requestVo);
    
}
