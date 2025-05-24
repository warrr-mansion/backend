package com.warrr.zipflex.api.member.dao;

import org.apache.ibatis.annotations.Mapper;
import com.warrr.zipflex.api.auth.dto.in.SignUpRequestDto;
import com.warrr.zipflex.api.member.domain.entity.Member;

@Mapper
public interface MemberDao {

    Member findByUuid(String uuid);

    void save(SignUpRequestDto requestDto);
    
}
