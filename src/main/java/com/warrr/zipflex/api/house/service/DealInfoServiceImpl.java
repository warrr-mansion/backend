package com.warrr.zipflex.api.house.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warrr.zipflex.api.house.dao.ApartmentDealDao;
import com.warrr.zipflex.api.house.dao.OfficetelDealDao;
import com.warrr.zipflex.api.house.dao.VillaDealDao;
import com.warrr.zipflex.api.house.dto.out.DealInfoResponseDto;

@Service
public class DealInfoServiceImpl implements DealInfoService {

    @Autowired
    private ApartmentDealDao apartmentDealDao;
    @Autowired
    private OfficetelDealDao officetelDealDao;
    @Autowired
    private VillaDealDao villaDealDao;

    @Override
    public List<DealInfoResponseDto> getDealInfoByFilter(String buildingType, String contractType,
                    String sgg, String emd) {
        if ("APARTMENT".equals(buildingType)) {
            return apartmentDealDao.findDealsByFilter(buildingType, contractType, sgg, emd);
        } else if ("VILLA".equals(buildingType)) {
            return villaDealDao.findDealsByFilter(contractType, sgg, emd);
        } else if ("OFFICETEL".equals(buildingType)) {
            return officetelDealDao.findDealsByFilter(contractType, sgg, emd);
        }

        return null;
    }

    @Override
    public List<DealInfoResponseDto> getDealInfoByHouseInfoId(String buildingType,
                    String houseInfoId) {
        if ("APARTMENT".equals(buildingType)) {
            return apartmentDealDao.findDealsById(houseInfoId);
        } else if ("VILLA".equals(buildingType)) {
            return villaDealDao.findDealsById(houseInfoId);
        } else if ("OFFICETEL".equals(buildingType)) {
            return officetelDealDao.findDealsById(houseInfoId);
        }

        return null;
    }

}
