package com.warrr.zipflex.api.house.service;

import java.util.List;

import com.warrr.zipflex.api.house.dto.out.DealInfoResponseDto;

public interface DealInfoService {
	
	List<DealInfoResponseDto> getDealInfoByFilter(String buildingType, String contractType, String sgg, String emd);
	
	List<DealInfoResponseDto> getDealInfoByHouseInfoId(String buildingType, String houseInfoId);

}
