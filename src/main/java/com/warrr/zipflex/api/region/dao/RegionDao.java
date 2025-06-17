package com.warrr.zipflex.api.region.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.warrr.zipflex.api.region.dto.out.RegionResponseDto;

@Mapper
public interface RegionDao {

    public List<RegionResponseDto> findAllSido();

    public List<RegionResponseDto> findAllGugun(String sidoCodePrefix);

    public List<RegionResponseDto> findAllDong(String gugunCodePrefix);

}
