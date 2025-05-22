package com.warrr.zipflex.api.house.service;

import com.warrr.zipflex.api.house.dto.out.DealInfoResponseDto;
import com.warrr.zipflex.global.support.CursorPage;
import com.warrr.zipflex.global.support.PageRequestDto;

public interface DealInfoService {

    // 기존 메서드
    // List<DealInfoResponseDto> getDealInfoByFilter(String buildingType, String contractType,
    // String sgg, String emd);
    //
    // List<DealInfoResponseDto> getDealInfoByHouseInfoId(String buildingType, String houseInfoId);

    // 총 항목 수 조회 메서드
    int getDealCountByFilter(String buildingType, String contractType, String sgg, String emd);

    int getDealCountByHouseInfoId(String buildingType, String houseInfoId);

    // 페이지네이션 메서드 (총 항목 수 조회 제거)
    CursorPage<DealInfoResponseDto> getDealInfoByFilterWithPagination(String buildingType,
                    String contractType, String sgg, String emd, PageRequestDto requestDto);

    CursorPage<DealInfoResponseDto> getDealInfoByHouseInfoIdWithPagination(String buildingType,
                    String houseInfoId, PageRequestDto requestDto);
}
