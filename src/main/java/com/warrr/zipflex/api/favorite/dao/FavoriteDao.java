package com.warrr.zipflex.api.favorite.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FavoriteDao {

    boolean existsByCodeAndMemberUuid(String code, String memberUuid);

    void insert(String code, String memberUuid);
    
}
