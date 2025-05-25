package com.warrr.zipflex.api.member.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.warrr.zipflex.api.auth.domain.model.AuthUserDetail;
import com.warrr.zipflex.api.member.dto.in.PasswordCheckRequestDto;
import com.warrr.zipflex.api.member.dto.out.MemberResponseDto;
import com.warrr.zipflex.api.member.dto.out.PasswordCheckResponseDto;
import com.warrr.zipflex.api.member.service.MemberService;
import com.warrr.zipflex.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Member")
@RequestMapping("/v1/members")
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @SecurityRequirement(name = "JWT")
    @Operation(summary = "회원 정보 상세 조회", description = "회원 본인만 조회 가능하며, 인증이 필요합니다.")
    @GetMapping
    public BaseResponse<MemberResponseDto> getMemberInfo(
                    @AuthenticationPrincipal AuthUserDetail authUserDetail) {

        return new BaseResponse<>(memberService.getMemberInfo(authUserDetail));
    }

    @SecurityRequirement(name = "JWT")
    @Operation(summary = "현재 비밀번호 일치 여부 검사", description = "비밀번호 변경 전에 입력한 현재 비밀번호가 맞는지 확인합니다.")
    @PostMapping("/password-check")
    public BaseResponse<PasswordCheckResponseDto> verifyPassword(
                    @AuthenticationPrincipal AuthUserDetail authUserDetail,
                    @RequestBody PasswordCheckRequestDto requestDto) {

        return new BaseResponse<>(memberService.checkPassword(authUserDetail,
                        requestDto.getCurrentPassword()));
    }

}
