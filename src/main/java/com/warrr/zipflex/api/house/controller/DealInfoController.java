package com.warrr.zipflex.api.house.controller;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.warrr.zipflex.api.house.dto.out.DealInfoResponseDto;
import com.warrr.zipflex.api.house.service.DealInfoService;

@RestController
@RequestMapping("/api/dealinfo")
public class DealInfoController {

    private final DealInfoService dealInfoService;
	
	public DealInfoController(DealInfoService dealInfoService) {
		this.dealInfoService = dealInfoService;
	}
	
	/**
     * 실거래가 조건별 조회
     *
     * @param buildingType 건물 유형 (apartment, villa, officetel) - 필수
     * @param contractType 거래 유형 (전세, 매매, 월세) - 선택
     * @param sgg         시군구 코드                              - 선택
     * @param emd        읍면동 코드                              - 선택
     * @return 조건에 맞는 거래 정보 목록
     */
	@GetMapping("/filter")
	public List<DealInfoResponseDto> getDealInfoByFilter(
			@RequestParam("buildingType") String buildingType,
			@RequestParam(value = "contractType", required = false) String contractType,
			@RequestParam(value = "sgg", required = false) String sgg,
			@RequestParam(value = "emd", required = false) String emd
	){
		return dealInfoService.getDealInfoByFilter(buildingType,  contractType, sgg, emd);
	}
	
	
	
	/**
     * 실거래가 house_info_id로 조회
     *
     * @param buildingType 건물 유형 (apartment, villa, officetel) - 필수
     * @param houseInfoId                               - 선택
     */
	@GetMapping("/id")
	public List<DealInfoResponseDto> getDealInfoByFilter(
			@RequestParam("buildingType") String buildingType,
			@RequestParam(value = "houseInfoId", required = false) String houseInfoId
	){
		return dealInfoService.getDealInfoByHouseInfoId(buildingType,  houseInfoId);
	}
	
	
	
}
