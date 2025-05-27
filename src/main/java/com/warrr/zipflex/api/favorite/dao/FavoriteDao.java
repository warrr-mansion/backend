package com.warrr.zipflex.api.favorite.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.warrr.zipflex.api.favorite.dto.in.FavoritePageRequestDto;

@Mapper
public interface FavoriteDao {

    boolean existsByCodeAndMemberUuid(String code, String memberUuid);

    void insert(String code, String memberUuid);

    void deleteByCodeAndMemberUuid(String code, String memberUuid);
    
    List<?> findAllByMemberUuidAndPage(String memberUuid, FavoritePageRequestDto requestDto);
    
}
