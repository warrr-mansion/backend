package com.warrr.zipflex.api.region.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.warrr.zipflex.api.region.dto.out.DongResponseDto;
import com.warrr.zipflex.api.region.dto.out.GugunResponseDto;
import com.warrr.zipflex.api.region.dto.out.SidoResponseDto;

@Mapper
public interface RegionDao {

    public List<SidoResponseDto> findAllSido();

    public List<GugunResponseDto> findAllGugun(String sidoCodePrefix);

    public List<DongResponseDto> findAllDong(String gugunCodePrefix);

}
