package com.warrr.zipflex.api.region.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.warrr.zipflex.api.region.dao.RegionDao;
import com.warrr.zipflex.api.region.dto.out.DongResponseDto;
import com.warrr.zipflex.api.region.dto.out.GugunResponseDto;
import com.warrr.zipflex.api.region.dto.out.SidoResponseDto;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionDao regionDao;

    @Override
    public List<SidoResponseDto> getAllSido() {
        return regionDao.findAllSido();
    }

    @Override
    public List<GugunResponseDto> getAllGugun(String sidoCode) {
        String sidoCodePrefix = sidoCode.substring(0, 2);
        return regionDao.findAllGugun(sidoCodePrefix);
    }

    @Override
    public List<DongResponseDto> getAllDong(String gugunCode) {
        String gugunCodePrefix = gugunCode.substring(0, 5);
        return regionDao.findAllDong(gugunCodePrefix);
    }
}
