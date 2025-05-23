package com.warrr.zipflex.api.region.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.warrr.zipflex.api.region.dto.DongDto;
import com.warrr.zipflex.api.region.dto.GugunDto;
import com.warrr.zipflex.api.region.dto.SidoDto;

@Mapper
public interface RegionDao {
    List<SidoDto> findAllSido();

    List<GugunDto> findAllGugun(String sidoCodePrefix);

    List<DongDto> findAllDong(String gugunCodePrefix);
}
