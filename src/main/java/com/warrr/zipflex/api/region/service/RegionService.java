package com.warrr.zipflex.api.region.service;

import java.util.List;
import com.warrr.zipflex.api.region.dto.out.RegionResponseDto;

public interface RegionService {
    
    List<RegionResponseDto> getAllSido();

    List<RegionResponseDto> getAllGugun(String sidoCode);

    List<RegionResponseDto> getAllDong(String gugunCode);

}
