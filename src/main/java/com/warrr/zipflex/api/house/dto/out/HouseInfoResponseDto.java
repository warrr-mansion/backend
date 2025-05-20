package com.warrr.zipflex.api.house.dto.out;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseInfoResponseDto {
    private long   id;
    private int   sigunguCode;
    private int   emdCode;
    private String emdName;
    private String roadName;
    private String buildingName;
    private BigDecimal latitude;
    private BigDecimal longitude;
}

