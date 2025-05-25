package com.warrr.zipflex.api.member.service;

import static com.warrr.zipflex.global.response.BaseResponseStatus.NO_EXIST_USER;
import static com.warrr.zipflex.global.response.BaseResponseStatus.NO_SIGN_IN;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.warrr.zipflex.api.auth.domain.model.AuthUserDetail;
import com.warrr.zipflex.api.member.dao.MemberDao;
import com.warrr.zipflex.api.member.dto.out.MemberResponseDto;
import com.warrr.zipflex.global.exception.BaseException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberDao memberDao;

    @Transactional(readOnly = true)
    @Override
    public MemberResponseDto getMemberInfo(AuthUserDetail authUserDetail) {
        return MemberResponseDto.fromEntity(
                        Optional.ofNullable(memberDao.findByUuid(
                                        Optional.ofNullable(authUserDetail.getUuid())
                                        .orElseThrow(() -> new BaseException(NO_SIGN_IN))))
                        .orElseThrow(() -> new BaseException(NO_EXIST_USER)));
    }
    
}
