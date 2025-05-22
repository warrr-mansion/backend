package com.warrr.zipflex.api.house.dao;

import com.warrr.zipflex.api.house.dto.out.DealInfoResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * 거래 정보 DAO 공통 인터페이스.
 */
@Mapper
public interface DealDao {
    // 기존 메서드
//    List<DealInfoResponseDto> findDealsByFilter(@Param("buildingType") String buildingType,
//                    @Param("contractType") String contractType, @Param("sgg") String sgg,
//                    @Param("emd") String emd);
//
//    List<DealInfoResponseDto> findDealsById(@Param("houseInfoId") String houseInfoId);

    // 오프셋 페이지네이션 적용 메서드
    List<DealInfoResponseDto> findDealsByFilterWithPagination(Map<String, Object> paramMap);

    List<DealInfoResponseDto> findDealsByIdWithPagination(Map<String, Object> paramMap);

    // 총 항목 수를 가져오는 메서드
    Integer countDealsByFilter(@Param("buildingType") String buildingType,
                    @Param("contractType") String contractType, @Param("sgg") String sgg,
                    @Param("emd") String emd);

    Integer countDealsById(@Param("houseInfoId") String houseInfoId);
}
