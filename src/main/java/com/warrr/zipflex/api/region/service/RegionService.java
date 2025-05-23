package com.warrr.zipflex.api.region.service;

import java.util.List;
import com.warrr.zipflex.api.region.dto.DongDto;
import com.warrr.zipflex.api.region.dto.GugunDto;
import com.warrr.zipflex.api.region.dto.SidoDto;

public interface RegionService {
    List<SidoDto> getAllSido();
    List<GugunDto> getAllGugun(String sidoCode);
    List<DongDto> getAllDong(String sidoCode);
}
