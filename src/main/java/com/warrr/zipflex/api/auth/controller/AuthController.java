package com.warrr.zipflex.api.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.warrr.zipflex.api.auth.service.AuthService;
import com.warrr.zipflex.api.auth.vo.in.SignUpRequestVo;
import com.warrr.zipflex.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Authentication")
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;
    
    @Operation(summary = "회원가입")
    @PostMapping("/sign-up")
    public BaseResponse<Void> signUp(@Valid @RequestBody SignUpRequestVo requestVo) {
        authService.signUp(requestVo);
        return new BaseResponse<>();
    }
    
}
