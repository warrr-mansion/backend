package com.warrr.zipflex.api.house.dao;

import com.warrr.zipflex.api.house.dto.out.DealInfoResponseDto;
import com.warrr.zipflex.api.house.dto.out.HouseInfoResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OfficetelDealDao {
    List<DealInfoResponseDto> findDealsByFilter(
    	@Param("contractType")	String contractType,
        @Param("sgg")     String sgg,
        @Param("emd")    String emd
    );
    
    List<DealInfoResponseDto> findDealsById(
    	@Param("houseInfoId")	String houseInfo
    );
}
