package com.warrr.zipflex.api.region.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.warrr.zipflex.api.region.dao.RegionDao;
import com.warrr.zipflex.api.region.dto.out.RegionResponseDto;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionDao regionDao;

    @Override
    public List<RegionResponseDto> getAllSido() {
        return regionDao.findAllSido();
    }

    @Override
    public List<RegionResponseDto> getAllGugun(String sidoCode) {
        String sidoCodePrefix = sidoCode.substring(0, 2);
        return regionDao.findAllGugun(sidoCodePrefix);
    }

    @Override
    public List<RegionResponseDto> getAllDong(String gugunCode) {
        String gugunCodePrefix = gugunCode.substring(0, 5);
        return regionDao.findAllDong(gugunCodePrefix);
    }
}
