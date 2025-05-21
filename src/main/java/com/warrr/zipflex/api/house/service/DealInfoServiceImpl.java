package com.warrr.zipflex.api.house.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.warrr.zipflex.api.house.dao.ApartmentDealDao;
import com.warrr.zipflex.api.house.dao.VillaDealDao;
import com.warrr.zipflex.api.house.dao.OfficetelDealDao;
import com.warrr.zipflex.api.house.dao.DealDao;
import com.warrr.zipflex.api.house.dto.out.DealInfoResponseDto;
import com.warrr.zipflex.global.support.PageRequestDto;
import com.warrr.zipflex.global.support.PageResponseDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DealInfoServiceImpl implements DealInfoService {
    @Autowired
    private ApartmentDealDao apartmentDealDao;

    @Autowired
    private VillaDealDao villaDealDao;

    @Autowired
    private OfficetelDealDao officetelDealDao;

    /**
     * 건물 유형에 따라 적절한 DAO를 선택하는 헬퍼 메서드
     */
    private DealDao getDaoByBuildingType(String buildingType) {
        if (buildingType == null) {
            throw new IllegalArgumentException("Building type cannot be null");
        }

        switch (buildingType.toUpperCase()) {
            case "APARTMENT":
                return apartmentDealDao;
            case "VILLA":
                return villaDealDao;
            case "OFFICETEL":
                return officetelDealDao;
            default:
                throw new IllegalArgumentException("Invalid building type: " + buildingType);
        }
    }

    @Override
    public List<DealInfoResponseDto> getDealInfoByFilter(String buildingType, String contractType,
                    String sgg, String emd) {
        DealDao dao = getDaoByBuildingType(buildingType);
        return dao.findDealsByFilter(buildingType, contractType, sgg, emd);
    }

    @Override
    public List<DealInfoResponseDto> getDealInfoByHouseInfoId(String buildingType,
                    String houseInfoId) {
        DealDao dao = getDaoByBuildingType(buildingType);
        return dao.findDealsById(houseInfoId);
    }

    @Override
    public PageResponseDto<DealInfoResponseDto> getDealInfoByFilterWithPagination(
                    String buildingType, String contractType, String sgg, String emd,
                    PageRequestDto requestDto) {

        // 페이지 요청 객체의 유효성 검증 및 기본값 설정
        if (requestDto == null) {
            requestDto = new PageRequestDto();
        }

        DealDao dao = getDaoByBuildingType(buildingType);

        // 총 항목 수 조회
        int totalCount = dao.countDealsByFilter(buildingType, contractType, sgg, emd);

        // 파라미터 맵 구성
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("buildingType", buildingType);
        paramMap.put("contractType", contractType);
        paramMap.put("sgg", sgg);
        paramMap.put("emd", emd);
        paramMap.put("pageRequest", requestDto);

        // 데이터 조회
        List<DealInfoResponseDto> dtoList = dao.findDealsByFilterWithPagination(paramMap);

        // PageResponseDto 객체 생성 및 반환
        return PageResponseDto.<DealInfoResponseDto>withAll().dtoList(dtoList)
                        .totalCount(totalCount).pageRequestDTO(requestDto).build();
    }

    @Override
    public PageResponseDto<DealInfoResponseDto> getDealInfoByHouseInfoIdWithPagination(
                    String buildingType, String houseInfoId, PageRequestDto requestDto) {

        // 페이지 요청 객체의 유효성 검증 및 기본값 설정
        if (requestDto == null) {
            requestDto = new PageRequestDto();
        }

        DealDao dao = getDaoByBuildingType(buildingType);

        // 총 항목 수 조회
        int totalCount = dao.countDealsById(houseInfoId);

        // 파라미터 맵 구성
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("houseInfoId", houseInfoId);
        paramMap.put("pageRequest", requestDto);

        // 데이터 조회
        List<DealInfoResponseDto> dtoList = dao.findDealsByIdWithPagination(paramMap);

        // PageResponseDto 객체 생성 및 반환
        return PageResponseDto.<DealInfoResponseDto>withAll().dtoList(dtoList)
                        .totalCount(totalCount).pageRequestDTO(requestDto).build();
    }
}
