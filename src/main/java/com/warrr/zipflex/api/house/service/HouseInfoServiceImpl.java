package com.warrr.zipflex.api.house.service;

import org.springframework.stereotype.Service;
import com.warrr.zipflex.api.house.dao.HouseInfoDao;
import com.warrr.zipflex.api.house.dto.out.HouseInfoResponseDto;
import com.warrr.zipflex.global.support.CursorPage;
import com.warrr.zipflex.global.support.PageRequestDto;
import lombok.RequiredArgsConstructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HouseInfoServiceImpl implements HouseInfoService {


    private final HouseInfoDao houseInfoDao;

    // 총 항목 수 조회 API 추가
    @Override
    public int getHouseCountByFilter(String buildingType, String contractType, String sgg,
                    String emd) {
        return houseInfoDao.countHouseByFilter(buildingType, contractType, sgg, emd);
    }

    @Override
    public CursorPage<HouseInfoResponseDto> findHouseInfoWithPagination(String buildingType,
                    String sgg, String emd, PageRequestDto requestDto) {

        if (requestDto == null) {
            requestDto = new PageRequestDto();
        }

        // 파라미터 맵 구성
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("buildingType", buildingType);
        paramMap.put("sgg", sgg);
        paramMap.put("emd", emd);
        paramMap.put("pageRequest", requestDto);
        paramMap.put("limitPlusOne", requestDto.getSize() + 1);

        // 데이터 조회
        List<HouseInfoResponseDto> dtoList = houseInfoDao.findHouseInfoWithPagination(paramMap);

        int pageSize = requestDto.getSize();
        int pageNo = requestDto.getPage();
        boolean hasNext = dtoList.size() > pageSize;

        if (hasNext) {
            dtoList = dtoList.subList(0, pageSize);
        }

        Long nextCursor = hasNext && !dtoList.isEmpty() ? dtoList.get(dtoList.size() - 1).getId() // 커서용
                                                                                                  // id
                        : null;

        return CursorPage.<HouseInfoResponseDto>builder().content(dtoList).pageSize(pageSize)
                        .pageNo(pageNo).hasNext(hasNext).nextCursor(nextCursor).build();
    }
}
