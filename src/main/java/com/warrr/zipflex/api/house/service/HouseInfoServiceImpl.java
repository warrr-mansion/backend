package com.warrr.zipflex.api.house.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.warrr.zipflex.api.house.dao.HouseInfoDao;
import com.warrr.zipflex.api.house.dto.out.HouseInfoResponseDto;

import java.util.List;

@Service
public class HouseInfoServiceImpl implements HouseInfoService {

    @Autowired
    private HouseInfoDao houseInfoDao;

    @Override
    public List<HouseInfoResponseDto> getHouseInfo(String buildingType, String sgg, String emd) {
        return houseInfoDao.findHouse(buildingType, sgg, emd);
    }
}
