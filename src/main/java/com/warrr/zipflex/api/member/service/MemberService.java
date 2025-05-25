package com.warrr.zipflex.api.member.service;

import com.warrr.zipflex.api.auth.domain.model.AuthUserDetail;
import com.warrr.zipflex.api.member.dto.out.MemberResponseDto;

public interface MemberService {

    MemberResponseDto getMemberInfo(AuthUserDetail authUserDetail);
    
}
