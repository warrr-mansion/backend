package com.warrr.zipflex.api.region.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.warrr.zipflex.api.region.dao.RegionDao;
import com.warrr.zipflex.api.region.dto.DongDto;
import com.warrr.zipflex.api.region.dto.GugunDto;
import com.warrr.zipflex.api.region.dto.SidoDto;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionDao regionDao;

    @Override
    public List<SidoDto> getAllSido() {
        return regionDao.findAllSido();
    }

    @Override
    public List<GugunDto> getAllGugun(String sidoCode) {
        String sidoCodePrefix = sidoCode.substring(0, 2);
        return regionDao.findAllGugun(sidoCodePrefix);
    }

    @Override
    public List<DongDto> getAllDong(String gugunCode) {
        String gugunCodePrefix = gugunCode.substring(0, 5);
        return regionDao.findAllDong(gugunCodePrefix);
    }
}
