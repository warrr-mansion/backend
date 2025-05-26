package com.warrr.zipflex.api.region.service;

import java.util.List;
import com.warrr.zipflex.api.region.dto.out.DongResponseDto;
import com.warrr.zipflex.api.region.dto.out.GugunResponseDto;
import com.warrr.zipflex.api.region.dto.out.SidoResponseDto;

public interface RegionService {
    List<SidoResponseDto> getAllSido();

    List<GugunResponseDto> getAllGugun(String sidoCode);

    List<DongResponseDto> getAllDong(String gugunCode);

}
