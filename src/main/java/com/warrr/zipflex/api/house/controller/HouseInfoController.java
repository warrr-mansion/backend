package com.warrr.zipflex.api.house.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.warrr.zipflex.api.house.dto.out.HouseInfoResponseDto;
import com.warrr.zipflex.api.house.service.HouseInfoService;

import java.util.List;

@RestController
@RequestMapping("/api/houseinfo")
public class HouseInfoController {

    private final HouseInfoService houseInfoService;

    public HouseInfoController(HouseInfoService houseInfoService) {
        this.houseInfoService = houseInfoService;
    }

    /**
     * 매물 조회
     *
     * @param buildingType 건물 유형 (apartment, villa, officetel) - 필수
     * @param sgg         시군구 코드                              - 선택
     * @param emd        읍면동 코드                              - 선택
     * @return 조건에 맞는 거래 정보 목록
     */
    @GetMapping
    public List<HouseInfoResponseDto> getHouseInfo(
            @RequestParam("buildingType") String buildingType,
            @RequestParam(value = "sgg",   required = false) String sgg,
            @RequestParam(value = "emd",  required = false) String emd
    ) {
        return houseInfoService.getHouseInfo(buildingType, sgg, emd);
    }
}