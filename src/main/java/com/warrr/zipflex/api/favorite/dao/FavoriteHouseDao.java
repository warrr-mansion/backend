package com.warrr.zipflex.api.favorite.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.warrr.zipflex.api.favorite.dto.in.FavoritePageRequestDto;
import com.warrr.zipflex.api.favorite.dto.out.FavoriteHouseResponseDto;

@Mapper
public interface FavoriteHouseDao extends FavoriteDao {

    @Override
    public List<FavoriteHouseResponseDto> findAllByMemberUuidAndPage(String memberUuid,
                    FavoritePageRequestDto requestDto);

}
