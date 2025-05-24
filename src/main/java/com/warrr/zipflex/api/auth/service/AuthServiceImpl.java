package com.warrr.zipflex.api.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.warrr.zipflex.api.auth.dto.in.SignUpRequestDto;
import com.warrr.zipflex.api.auth.vo.in.SignUpRequestVo;
import com.warrr.zipflex.api.member.dao.MemberDao;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final MemberDao memberDao;
    
    private final PasswordEncoder passwordEncoder;
    
    @Transactional
    @Override
    public void signUp(SignUpRequestVo requestVo) {
        memberDao.save(SignUpRequestDto.toDto(requestVo, 
                        passwordEncoder.encode(requestVo.getPassword())));
    }
    
}
