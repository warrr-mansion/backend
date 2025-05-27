package com.warrr.zipflex.api.house.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.warrr.zipflex.api.house.dto.out.HouseInfoPageRequestDto;
import com.warrr.zipflex.api.house.dto.out.HouseInfoResponseDto;

@Mapper
public interface HouseInfoDao {

    List<HouseInfoResponseDto> findHouseInfoWithPagination(HouseInfoPageRequestDto requestDto);

    int countHouseInfo(String buildingType, String sgg, String emd);

    int countHouseByFilter(String buildingType, String contractType, String sgg, String emd);

}
