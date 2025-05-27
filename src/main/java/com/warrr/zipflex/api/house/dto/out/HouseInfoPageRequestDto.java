package com.warrr.zipflex.api.house.dto.out;

import com.warrr.zipflex.api.house.domain.model.BuildingType;
import com.warrr.zipflex.global.support.PageRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class HouseInfoPageRequestDto extends PageRequestDto {

    private BuildingType buildingType;
    private String sigunguCode;
    private String emdCode;

    @Builder
    public HouseInfoPageRequestDto(int pageNo, int pageSize, BuildingType buildingType,
                    String sigunguCode, String emdCode) {

        super(pageNo, pageSize);
        this.buildingType = buildingType;
        this.sigunguCode = sigunguCode;
        this.emdCode = emdCode;
    }

    public static HouseInfoPageRequestDto toDto(BuildingType buildingType, String sgg, String emd,
                    int pageNo, int pageSize) {

        return HouseInfoPageRequestDto.builder().pageNo(pageNo).pageSize(pageSize)
                        .buildingType(buildingType).sigunguCode(sgg).emdCode(emd).build();
    }

}
