package com.warrr.zipflex.api.favorite.dto.in;

import com.warrr.zipflex.api.favorite.domain.model.FavoriteType;
import com.warrr.zipflex.global.support.PageRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class FavoritePageRequestDto extends PageRequestDto {

    private FavoriteType favoriteType;

    @Builder
    public FavoritePageRequestDto(int pageNo, int pageSize, FavoriteType favoriteType) {
        super(pageNo, pageSize);
        this.favoriteType = favoriteType;
    }
    
    public static FavoritePageRequestDto toDto(FavoriteType favoriteType, int pageNo, int pageSize) {
        return FavoritePageRequestDto.builder()
                        .favoriteType(favoriteType)
                        .pageNo(pageNo)
                        .pageSize(pageSize)
                        .build();
    }
    
}
