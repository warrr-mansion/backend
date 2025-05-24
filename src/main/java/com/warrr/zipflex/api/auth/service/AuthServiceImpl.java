package com.warrr.zipflex.api.auth.service;

import static com.warrr.zipflex.global.response.BaseResponseStatus.FAILED_TO_LOGIN;
import static com.warrr.zipflex.api.auth.domain.model.TokenType.ACCESS_TOKEN;
import static com.warrr.zipflex.api.auth.domain.model.TokenType.REFRESH_TOKEN;

import java.util.Optional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.warrr.zipflex.api.auth.dto.in.SignInRequestDto;
import com.warrr.zipflex.api.auth.dto.in.SignUpRequestDto;
import com.warrr.zipflex.api.auth.dto.out.JwtTokenResponseDto;
import com.warrr.zipflex.api.auth.dto.out.SignInResponseDto;
import com.warrr.zipflex.api.auth.vo.in.SignUpRequestVo;
import com.warrr.zipflex.api.member.dao.MemberDao;
import com.warrr.zipflex.global.exception.BaseException;
import com.warrr.zipflex.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final MemberDao memberDao;
    private final JwtTokenService jwtTokenService;
    
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void signUp(SignUpRequestVo requestVo) {
        memberDao.save(SignUpRequestDto.toDto(requestVo,
                        passwordEncoder.encode(requestVo.getPassword())));
    }

    @Transactional(readOnly = true)
    @Override
    public JwtTokenResponseDto signIn(SignInRequestDto requestDto) {
        
        SignInResponseDto signInMember = Optional.ofNullable(memberDao.findByEmail(requestDto.getEmail()))
                        .orElseThrow(() -> new BaseException(FAILED_TO_LOGIN));
        Authentication authentication =
                        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                                        signInMember.getMemberUuid(), requestDto.getPassword()));
        String refreshToken = jwtTokenProvider.generateToken(authentication, REFRESH_TOKEN);
        
        jwtTokenService.saveRefreshToken(signInMember.getMemberUuid(), refreshToken);
        return JwtTokenResponseDto.builder()
                        .accessToken(jwtTokenProvider.generateToken(authentication, ACCESS_TOKEN))
                        .refreshToken(refreshToken)
                        .uuid(signInMember.getMemberUuid())
                        .build();
    }
}
