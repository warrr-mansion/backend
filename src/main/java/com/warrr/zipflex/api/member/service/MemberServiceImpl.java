package com.warrr.zipflex.api.member.service;

import static com.warrr.zipflex.global.response.BaseResponseStatus.NO_EXIST_USER;
import static com.warrr.zipflex.global.response.BaseResponseStatus.NO_SIGN_IN;
import static com.warrr.zipflex.global.response.BaseResponseStatus.PASSWORD_NOT_MATCHED;
import static com.warrr.zipflex.global.response.BaseResponseStatus.PASSWORD_SAME_AS_CURRENT;

import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.warrr.zipflex.api.auth.domain.model.AuthUserDetail;
import com.warrr.zipflex.api.member.controller.NicknameUpdateRequestDto;
import com.warrr.zipflex.api.member.dao.MemberDao;
import com.warrr.zipflex.api.member.domain.entity.Member;
import com.warrr.zipflex.api.member.dto.in.PasswordUpdateRequestDto;
import com.warrr.zipflex.api.member.dto.out.MemberResponseDto;
import com.warrr.zipflex.api.member.dto.out.PasswordCheckResponseDto;
import com.warrr.zipflex.global.exception.BaseException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberDao memberDao;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    @Override
    public MemberResponseDto getMemberInfo(AuthUserDetail authUserDetail) {
        return MemberResponseDto.fromEntity(getAuthenticatedMember(authUserDetail));
    }

    @Transactional(readOnly = true)
    @Override
    public PasswordCheckResponseDto checkPassword(AuthUserDetail authUserDetail,
                    String currentPassword) {

        return new PasswordCheckResponseDto(passwordEncoder.matches(currentPassword,
                        getAuthenticatedMember(authUserDetail).getPassword()));
    }

    @Transactional
    @Override
    public void changePassword(AuthUserDetail authUserDetail, PasswordUpdateRequestDto requestDto) {
        Member member = getAuthenticatedMember(authUserDetail);

        if (passwordEncoder.matches(requestDto.getNewPassword(), member.getPassword())) {
            throw new BaseException(PASSWORD_SAME_AS_CURRENT);
        }
        if (!passwordEncoder.matches(requestDto.getCurrentPassword(), member.getPassword())) {
            throw new BaseException(PASSWORD_NOT_MATCHED);
        }

        memberDao.updatePassword(authUserDetail.getUuid(),
                        passwordEncoder.encode(requestDto.getNewPassword()));
    }

    @Transactional
    @Override
    public void changeNickname(AuthUserDetail authUserDetail, NicknameUpdateRequestDto requestDto) {
        memberDao.updateNickname(getAuthenticatedMember(authUserDetail).getMemberUuid(),
                        requestDto.getNickname());
    }

    private Member getAuthenticatedMember(AuthUserDetail authUserDetail) {
        return Optional.ofNullable(memberDao.findByUuid(Optional.ofNullable(authUserDetail)
                        .orElseThrow(() -> new BaseException(NO_SIGN_IN)).getUuid()))
                        .orElseThrow(() -> new BaseException(NO_EXIST_USER));
    }

}
