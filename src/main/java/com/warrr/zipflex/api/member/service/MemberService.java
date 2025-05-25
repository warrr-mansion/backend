package com.warrr.zipflex.api.member.service;

import com.warrr.zipflex.api.auth.domain.model.AuthUserDetail;
import com.warrr.zipflex.api.member.controller.NicknameUpdateRequestDto;
import com.warrr.zipflex.api.member.dto.in.PasswordUpdateRequestDto;
import com.warrr.zipflex.api.member.dto.out.MemberResponseDto;
import com.warrr.zipflex.api.member.dto.out.PasswordCheckResponseDto;

public interface MemberService {

    MemberResponseDto getMemberInfo(AuthUserDetail authUserDetail);

    PasswordCheckResponseDto checkPassword(AuthUserDetail authUserDetail, String currentPassword);

    void changePassword(AuthUserDetail authUserDetail, PasswordUpdateRequestDto requestDto);

    void changeNickname(AuthUserDetail authUserDetail, NicknameUpdateRequestDto requestDto);

}
