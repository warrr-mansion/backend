package com.warrr.zipflex.api.house.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.warrr.zipflex.api.house.domain.model.BuildingType;
import com.warrr.zipflex.api.house.dto.out.HouseInfoPageRequestDto;
import com.warrr.zipflex.api.house.dto.out.HouseInfoResponseDto;
import com.warrr.zipflex.api.house.service.HouseInfoService;
import com.warrr.zipflex.global.response.BaseResponse;
import com.warrr.zipflex.global.support.CursorPage;
import com.warrr.zipflex.global.support.PageRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "HouseInfo")
@RestController
@RequestMapping("/v1/houses")
@RequiredArgsConstructor
public class HouseInfoController {
    
    private final HouseInfoService houseInfoService;

    /**
     * 매물 조회 (오프셋 페이지네이션).
     *
     * @param buildingType 건물 유형 (apartment, villa, officetel) - 필수
     * @param sgg 시군구 코드 - 선택
     * @param emd 읍면동 코드 - 선택
     * @param requestDto 페이지 요청 정보 (페이지 번호, 페이지 크기)
     * @return 오프셋 페이지네이션이 적용된 매물 정보 목록.
     */
    @Operation(summary = "매물 조회 (페이지네이션)", description = "건물 유형에 따른 매물 정보를 페이지 단위로 조회합니다")
    @GetMapping("/type/{buildingType}")
    public BaseResponse<CursorPage<HouseInfoResponseDto>> getHouseInfoWithPagination(
                    @PathVariable BuildingType buildingType,
                    @RequestParam String sgg,
                    @RequestParam String emd,
                    @ModelAttribute PageRequestDto requestDto) {
        
        return new BaseResponse<>((CursorPage<HouseInfoResponseDto>) houseInfoService
                        .findHouseInfoWithPagination(HouseInfoPageRequestDto.toDto(buildingType, sgg, emd, 
                                        requestDto.getPageNo(), requestDto.getPageSize())));
        
    }
}
