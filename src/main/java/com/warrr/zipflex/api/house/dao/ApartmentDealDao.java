package com.warrr.zipflex.api.house.dao;

import com.warrr.zipflex.api.house.dto.out.DealInfoResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ApartmentDealDao {
	List<DealInfoResponseDto> findDealsByFilter(
		    @Param("buildingType") String buildingType,
		    @Param("contractType") String contractType,
		    @Param("sgg") String sgg,
		    @Param("emd") String emd
		);
    
    List<DealInfoResponseDto> findDealsById(
    	@Param("houseInfoId")	String houseInfo
    );
}
