package com.warrr.zipflex.api.favorite.dto.in;

import com.warrr.zipflex.api.favorite.domain.model.FavoriteType;
import com.warrr.zipflex.global.support.PageRequestDto;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@SuperBuilder
public class FavoritePageRequestDto extends PageRequestDto {

    private FavoriteType favoriteType;
    
    public static FavoritePageRequestDto toDto(FavoriteType favoriteType, int pageNo, int pageSize) {
        return FavoritePageRequestDto.builder()
                        .favoriteType(favoriteType)
                        .pageNo(pageNo)
                        .pageSize(pageSize)
                        .build();
    }
    
}
