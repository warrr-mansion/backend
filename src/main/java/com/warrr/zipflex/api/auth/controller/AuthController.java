package com.warrr.zipflex.api.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.warrr.zipflex.api.auth.dto.in.ReIssueTokenRequestDto;
import com.warrr.zipflex.api.auth.dto.in.SignInRequestDto;
import com.warrr.zipflex.api.auth.dto.out.JwtTokenResponseDto;
import com.warrr.zipflex.api.auth.service.AuthService;
import com.warrr.zipflex.api.auth.vo.in.SignInRequestVo;
import com.warrr.zipflex.api.auth.vo.in.SignUpRequestVo;
import com.warrr.zipflex.api.auth.vo.out.SignInResponseVo;
import com.warrr.zipflex.global.jwt.properties.JwtProperties;
import com.warrr.zipflex.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Authentication")
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;
    private final JwtProperties jwtProperties;

    @Operation(summary = "회원가입")
    @PostMapping("/sign-up")
    public BaseResponse<Void> signUp(@Valid @RequestBody SignUpRequestVo requestVo) {
        authService.signUp(requestVo);
        return new BaseResponse<>();
    }

    @Operation(summary = "로그인")
    @PostMapping("/sign-in")
    public BaseResponse<SignInResponseVo> signIn(@Valid @RequestBody SignInRequestVo requestVo,
                    HttpServletResponse response) {

        JwtTokenResponseDto responseDto = authService.signIn(SignInRequestDto.toDto(requestVo));

        response.setHeader(jwtProperties.getAccessTokenPrefix(), responseDto.getAccessToken());
        response.setHeader(jwtProperties.getRefreshTokenPrefix(), responseDto.getRefreshToken());
        return new BaseResponse<>(responseDto.toVo());
    }

    @Operation(summary = "Access Token 재발급")
    @PostMapping("/reissue")
    public BaseResponse<Void> reissue(@RequestBody ReIssueTokenRequestDto requestDto,
                    HttpServletResponse response) {

        response.setHeader(jwtProperties.getAccessTokenPrefix(),
                        authService.reissueAccessToken(requestDto.getRefreshToken()));
        return new BaseResponse<>();
    }

}
