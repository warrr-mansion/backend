package com.warrr.zipflex.api.house.service;

import java.util.List;
import com.warrr.zipflex.api.house.dto.out.DealInfoResponseDto;
import com.warrr.zipflex.global.support.PageRequestDto;
import com.warrr.zipflex.global.support.PageResponseDto;

public interface DealInfoService {
    // 기존 메서드
    List<DealInfoResponseDto> getDealInfoByFilter(String buildingType, String contractType,
                    String sgg, String emd);

    List<DealInfoResponseDto> getDealInfoByHouseInfoId(String buildingType, String houseInfoId);

    // 오프셋 페이지네이션 메서드
    PageResponseDto<DealInfoResponseDto> getDealInfoByFilterWithPagination(String buildingType,
                    String contractType, String sgg, String emd, PageRequestDto requestDto);

    PageResponseDto<DealInfoResponseDto> getDealInfoByHouseInfoIdWithPagination(String buildingType,
                    String houseInfoId, PageRequestDto requestDto);
}
