package com.warrr.zipflex.api.favorite.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.warrr.zipflex.api.favorite.dto.in.FavoritePageRequestDto;
import com.warrr.zipflex.api.favorite.dto.out.FavoriteRegionResponseDto;

@Mapper
public interface FavoriteRegionDao extends FavoriteDao {

    @Override
    public List<FavoriteRegionResponseDto> findAllByMemberUuidAndPage(String memberUuid,
                    FavoritePageRequestDto requestDto);

}
