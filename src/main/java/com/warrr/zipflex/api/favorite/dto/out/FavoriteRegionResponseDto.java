package com.warrr.zipflex.api.favorite.dto.out;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class FavoriteRegionResponseDto {

    private Long id;
    private String regionCode;
    private String sidoName;
    private String gugunName;
    private String dongName;
    
}
