package com.warrr.zipflex.api.house.dao;

import com.warrr.zipflex.api.house.dto.out.HouseInfoResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HouseInfoDao {
    List<HouseInfoResponseDto> findHouse(
    	@Param("buildingType") String buildingType,
        @Param("sgg")     String sgg,
        @Param("emd")     String emd
    );
}