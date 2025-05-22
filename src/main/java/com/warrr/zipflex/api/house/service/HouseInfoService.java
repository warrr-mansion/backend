package com.warrr.zipflex.api.house.service;

import com.warrr.zipflex.api.house.dto.out.HouseInfoResponseDto;
import com.warrr.zipflex.global.support.CursorPage;
import com.warrr.zipflex.global.support.PageRequestDto;

public interface HouseInfoService {
    // 기존 메서드
    // List<HouseInfoResponseDto> getHouseInfo(String buildingType, String sgg, String emd);
    
    // 총 항목 수 조회 메서드
    int getHouseCountByFilter(String buildingType, String contractType, String sgg, String emd);

    // 오프셋 페이지네이션 메서드(총 항목 수 조회 제거)
    CursorPage<HouseInfoResponseDto> findHouseInfoWithPagination(String buildingType,
                    String sgg, String emd, PageRequestDto requestDto);

    
}
