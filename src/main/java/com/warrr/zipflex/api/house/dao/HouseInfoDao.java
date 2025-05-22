package com.warrr.zipflex.api.house.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.warrr.zipflex.api.house.dto.out.DealInfoResponseDto;
import com.warrr.zipflex.api.house.dto.out.HouseInfoResponseDto;
import com.warrr.zipflex.global.response.BaseResponse;
import com.warrr.zipflex.global.support.CursorPage;

@Mapper
public interface HouseInfoDao {
    // 기존 메서드
//    List<HouseInfoResponseDto> findHouseInfo(String buildingType, String sgg, String emd);
    
    // 오프셋 페이지네이션 메서드
    List<HouseInfoResponseDto> findHouseInfoWithPagination(Map<String, Object> paramMap);
    
    // 총 항목 수 조회 메서드
    int countHouseInfo(String buildingType, String sgg, String emd);
}