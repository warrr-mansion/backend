package com.warrr.zipflex.api.house.service;

import java.util.List;

import com.warrr.zipflex.api.house.dto.out.HouseInfoResponseDto;

public interface HouseInfoService {

    List<HouseInfoResponseDto> getHouseInfo(String buildingType, String sgg, String emd);

}
