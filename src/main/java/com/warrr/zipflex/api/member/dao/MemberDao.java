package com.warrr.zipflex.api.member.dao;

import org.apache.ibatis.annotations.Mapper;
import com.warrr.zipflex.api.auth.dto.in.SignUpRequestDto;
import com.warrr.zipflex.api.auth.dto.out.SignInResponseDto;
import com.warrr.zipflex.api.member.domain.entity.Member;

@Mapper
public interface MemberDao {

    void save(SignUpRequestDto requestDto);

    Member findByUuid(String uuid);

    SignInResponseDto findByEmail(String email);

}
