package com.warrr.zipflex.api.member.dao;

import org.apache.ibatis.annotations.Mapper;
import com.warrr.zipflex.api.member.domain.entity.Member;

@Mapper
public interface MemberDao {

    Member findByUuid(String uuid);

}
