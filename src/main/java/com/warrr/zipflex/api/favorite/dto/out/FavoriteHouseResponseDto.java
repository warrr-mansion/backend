package com.warrr.zipflex.api.favorite.dto.out;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class FavoriteHouseResponseDto {

    private Long id;
    private Long houseInfoId;
    private Integer sigunguCode;
    private Integer emdCode;
    private String emdName;
    private String houseType;
    private String jibun;
    private String roadName;
    private String buildingName;
    private Integer buildYear;
    private Double latitude;
    private Double longitude;
    
}
