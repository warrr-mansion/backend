package com.warrr.zipflex.api.house.service;

import com.warrr.zipflex.api.house.dto.out.HouseInfoPageRequestDto;
import com.warrr.zipflex.api.house.dto.out.HouseInfoResponseDto;
import com.warrr.zipflex.global.support.CursorPage;

public interface HouseInfoService {
    
    // 총 항목 수 조회 메서드
    int getHouseCountByFilter(String buildingType, String contractType, String sgg, String emd);

    // 오프셋 페이지네이션 메서드(총 항목 수 조회 제거)
    CursorPage<HouseInfoResponseDto> findHouseInfoWithPagination(HouseInfoPageRequestDto requestDto);

}
