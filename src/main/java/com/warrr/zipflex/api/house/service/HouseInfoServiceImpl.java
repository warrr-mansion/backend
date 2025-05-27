package com.warrr.zipflex.api.house.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.warrr.zipflex.api.house.dao.HouseInfoDao;
import com.warrr.zipflex.api.house.dto.out.HouseInfoPageRequestDto;
import com.warrr.zipflex.api.house.dto.out.HouseInfoResponseDto;
import com.warrr.zipflex.global.support.CursorPage;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HouseInfoServiceImpl implements HouseInfoService {


    private final HouseInfoDao houseInfoDao;

    @Override
    public int getHouseCountByFilter(String buildingType, String contractType, String sgg,
                    String emd) {
        return houseInfoDao.countHouseByFilter(buildingType, contractType, sgg, emd);
    }

    @Override
    public CursorPage<HouseInfoResponseDto> findHouseInfoWithPagination(HouseInfoPageRequestDto requestDto) {

        List<HouseInfoResponseDto> dtoList = houseInfoDao.findHouseInfoWithPagination(requestDto);

        int pageSize = requestDto.getPageSize();
        int pageNo = requestDto.getPageNo();
        boolean hasNext = dtoList.size() > pageSize;

        if (hasNext) {
            dtoList = dtoList.subList(0, pageSize);
        }

        Long nextCursor = hasNext && !dtoList.isEmpty() ? dtoList.get(dtoList.size() - 1).getId() : null;

        // TODO: 조회 수 증가 및 최근 본 매물 리스트에 추가
        
        return CursorPage.<HouseInfoResponseDto>builder().content(dtoList).pageSize(pageSize)
                        .pageNo(pageNo).hasNext(hasNext).nextCursor(nextCursor).build();
    }
}
