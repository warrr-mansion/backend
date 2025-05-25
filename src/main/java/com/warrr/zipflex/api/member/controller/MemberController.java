package com.warrr.zipflex.api.member.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.warrr.zipflex.api.auth.domain.model.AuthUserDetail;
import com.warrr.zipflex.api.member.dto.out.MemberResponseDto;
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
    
}
