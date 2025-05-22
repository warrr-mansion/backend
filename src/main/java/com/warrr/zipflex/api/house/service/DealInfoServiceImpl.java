package com.warrr.zipflex.api.house.service;

import static com.warrr.zipflex.global.response.BaseResponseStatus.INVALID_BUILDING_TYPE;



import org.springframework.stereotype.Service;
import com.warrr.zipflex.api.house.dao.ApartmentDealDao;
import com.warrr.zipflex.api.house.dao.VillaDealDao;
import com.warrr.zipflex.api.house.dao.OfficetelDealDao;
import com.warrr.zipflex.api.house.dao.DealDao;
import com.warrr.zipflex.api.house.dto.out.DealInfoResponseDto;
import com.warrr.zipflex.global.exception.BaseException;
import com.warrr.zipflex.global.support.CursorPage;
import com.warrr.zipflex.global.support.PageRequestDto;
import lombok.RequiredArgsConstructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DealInfoServiceImpl implements DealInfoService {

    private final ApartmentDealDao apartmentDealDao;
    private final VillaDealDao villaDealDao;
    private final OfficetelDealDao officetelDealDao;

    /**
     * 건물 유형에 따라 적절한 DAO를 선택하는 헬퍼 메서드.
     */
    private DealDao getDaoByBuildingType(String buildingType) {

        switch (buildingType.toUpperCase()) {
            case "APARTMENT":
                return apartmentDealDao;
            case "VILLA":
                return villaDealDao;
            case "OFFICETEL":
                return officetelDealDao;
            default:
                throw new BaseException(INVALID_BUILDING_TYPE);
        }
    }

    // @Override
    // public List<DealInfoResponseDto> getDealInfoByFilter(String buildingType, String
    // contractType,
    // String sgg, String emd) {
    // DealDao dao = getDaoByBuildingType(buildingType);
    // return dao.findDealsByFilter(buildingType, contractType, sgg, emd);
    // }
    //
    // @Override
    // public List<DealInfoResponseDto> getDealInfoByHouseInfoId(String buildingType,
    // String houseInfoId) {
    // DealDao dao = getDaoByBuildingType(buildingType);
    // return dao.findDealsById(houseInfoId);
    // }

    // 총 항목 수 조회 API 추가
    @Override
    public int getDealCountByFilter(String buildingType, String contractType, String sgg,
                    String emd) {
        DealDao dao = getDaoByBuildingType(buildingType);
        return dao.countDealsByFilter(buildingType, contractType, sgg, emd);
    }

    @Override
    public int getDealCountByHouseInfoId(String buildingType, String houseInfoId) {
        DealDao dao = getDaoByBuildingType(buildingType);
        return dao.countDealsById(houseInfoId);
    }

    @Override
    public CursorPage<DealInfoResponseDto> getDealInfoByFilterWithPagination(String buildingType,
                    String contractType, String sgg, String emd, PageRequestDto requestDto) {

        DealDao dao = getDaoByBuildingType(buildingType);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("buildingType", buildingType);
        paramMap.put("contractType", contractType);
        paramMap.put("sgg", sgg);
        paramMap.put("emd", emd);
        paramMap.put("pageRequest", requestDto);
        paramMap.put("limitPlusOne", requestDto.getSize() + 1);

        List<DealInfoResponseDto> deals = dao.findDealsByFilterWithPagination(paramMap);

        int pageSize = requestDto.getSize();
        int pageNo = requestDto.getPage();
        boolean hasNext = deals.size() > pageSize;

        if (hasNext) {
            deals = deals.subList(0, pageSize); // 다음 페이지 존재하므로 1개 더 온 것 자르기
        }

        Long nextCursor = hasNext && !deals.isEmpty() ? deals.get(deals.size() - 1).getId() : null;

        return CursorPage.<DealInfoResponseDto>builder().content(deals).pageSize(pageSize)
                        .pageNo(pageNo).hasNext(hasNext).nextCursor(nextCursor).build();
    }


    @Override
    public CursorPage<DealInfoResponseDto> getDealInfoByHouseInfoIdWithPagination(
                    String buildingType, String houseInfoId, PageRequestDto requestDto) {

        DealDao dao = getDaoByBuildingType(buildingType);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("houseInfoId", houseInfoId);
        paramMap.put("pageRequest", requestDto);
        paramMap.put("limitPlusOne", requestDto.getSize() + 1);

        List<DealInfoResponseDto> deals = dao.findDealsByIdWithPagination(paramMap);

        int pageSize = requestDto.getSize();
        int pageNo = requestDto.getPage();
        boolean hasNext = deals.size() > pageSize;

        if (hasNext) {
            deals = deals.subList(0, pageSize);
        }

        Long nextCursor = hasNext && !deals.isEmpty() ? deals.get(deals.size() - 1).getId() : null;

        return CursorPage.<DealInfoResponseDto>builder().content(deals).pageSize(pageSize)
                        .pageNo(pageNo).hasNext(hasNext).nextCursor(nextCursor).build();
    }

}
