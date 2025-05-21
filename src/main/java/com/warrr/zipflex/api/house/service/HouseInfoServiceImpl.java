package com.warrr.zipflex.api.house.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.warrr.zipflex.api.house.dao.HouseInfoDao;
import com.warrr.zipflex.api.house.dto.out.HouseInfoResponseDto;
import com.warrr.zipflex.global.support.PageRequestDto;
import com.warrr.zipflex.global.support.PageResponseDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HouseInfoServiceImpl implements HouseInfoService {

    @Autowired
    private HouseInfoDao houseInfoDao;

    @Override
    public List<HouseInfoResponseDto> getHouseInfo(String buildingType, String sgg, String emd) {
        return houseInfoDao.findHouseInfo(buildingType, sgg, emd);
    }

    @Override
    public PageResponseDto<HouseInfoResponseDto> findHouseInfoWithPagination(
            String buildingType,
            String sgg,
            String emd,
            PageRequestDto requestDto) {

        // 총 항목 수 조회
        final int totalCount = houseInfoDao.countHouseInfo(buildingType, sgg, emd);

        // 페이지 요청 객체의 유효성 검증 및 기본값 설정
        if (requestDto == null) {
            requestDto = new PageRequestDto();
        }

        // 파라미터 맵 구성
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("buildingType", buildingType);
        paramMap.put("sgg", sgg);
        paramMap.put("emd", emd);
        paramMap.put("pageRequest", requestDto);

        // 데이터 조회
        List<HouseInfoResponseDto> dtoList = houseInfoDao.findHouseInfoWithPagination(paramMap);

        // PageResponseDto 객체 생성 및 반환
        return PageResponseDto.<HouseInfoResponseDto>withAll()
                .dtoList(dtoList)
                .totalCount(totalCount)
                .pageRequestDto(requestDto)
                .build();
    }
}