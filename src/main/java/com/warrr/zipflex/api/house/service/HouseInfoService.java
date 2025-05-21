package com.warrr.zipflex.api.house.service;

import java.util.List;
import com.warrr.zipflex.api.house.dto.out.HouseInfoResponseDto;
import com.warrr.zipflex.global.support.PageRequestDto;
import com.warrr.zipflex.global.support.PageResponseDto;

public interface HouseInfoService {
    // 기존 메서드
    List<HouseInfoResponseDto> getHouseInfo(String buildingType, String sgg, String emd);

    // 오프셋 페이지네이션 메서드
    PageResponseDto<HouseInfoResponseDto> findHouseInfoWithPagination(String buildingType, String sgg,
                                                                     String emd, PageRequestDto requestDto);
}