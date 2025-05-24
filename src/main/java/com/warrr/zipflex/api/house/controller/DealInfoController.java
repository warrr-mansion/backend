package com.warrr.zipflex.api.house.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.warrr.zipflex.api.house.dto.out.DealInfoResponseDto;
import com.warrr.zipflex.api.house.service.DealInfoService;
import com.warrr.zipflex.global.response.BaseResponse;
import com.warrr.zipflex.global.support.CursorPage;
import com.warrr.zipflex.global.support.PageRequestDto;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "HouseDeal")
@RestController
@RequestMapping("/v1/houses")
@RequiredArgsConstructor
public class DealInfoController {
    private final DealInfoService dealInfoService;



    // /**
    // * 실거래가 조건별 총 항목 수 조회.
    // *
    // * @param buildingType 건물 유형 (apartment, villa, officetel) - 필수
    // * @param contractType 거래 유형 (전세, 매매, 월세) - 선택
    // * @param sgg 시군구 코드 - 선택
    // * @param emd 읍면동 코드 - 선택
    // * @return 조건에 맞는 거래 정보의 총 개수.
    // */
    // @Operation(summary = "실거래가 조건별 총 항목 수 조회", description = "조건에 맞는 실거래가 정보의 총 개수를 조회합니다")
    // @GetMapping("/count/type/{buildingType}")
    // public BaseResponse<Integer> getDealCountByFilter(
    // @Parameter(description = "건물 유형 (apartment, villa, officetel)",
    // required = true) @PathVariable String buildingType,
    // @Parameter(description = "거래 유형 (전세, 매매, 월세)") @RequestParam(
    // value = "contractType", required = false) String contractType,
    // @Parameter(description = "시군구 코드") @RequestParam(value = "sgg",
    // required = false) String sgg,
    // @Parameter(description = "읍면동 코드") @RequestParam(value = "emd",
    // required = false) String emd) {
    // return new BaseResponse<>(
    // dealInfoService.getDealCountByFilter(buildingType, contractType, sgg, emd));
    // }

    // /**
    // * 실거래가 house_info_id별 총 항목 수 조회.
    // *
    // * @param id house_info_id - 필수
    // * @param buildingType 건물 유형 (apartment, villa, officetel) - 필수
    // * @return 해당 house_info_id에 맞는 거래 정보의 총 개수.
    // */
    // @Operation(summary = "실거래가 ID별 총 항목 수 조회",
    // description = "특정 house_info_id에 해당하는 실거래가 정보의 총 개수를 조회합니다")
    // @GetMapping("/count/{id}")
    // public BaseResponse<Integer> getDealCountByHouseInfoId(
    // @Parameter(description = "house_info_id",
    // required = true) @PathVariable String id,
    // @Parameter(description = "건물 유형 (apartment, villa, officetel)",
    // required = true) @RequestParam String buildingType) {
    // return new BaseResponse<>(dealInfoService.getDealCountByHouseInfoId(buildingType, id));
    // }

    /**
     * 실거래가 조건별 조회 (오프셋 페이지네이션).
     *
     * @param buildingType 건물 유형 (apartment, villa, officetel) - 필수
     * @param contractType 거래 유형 (전세, 매매, 월세) - 선택
     * @param sgg 시군구 코드 - 선택
     * @param emd 읍면동 코드 - 선택
     * @param requestDto 페이지 요청 정보
     * @return 오프셋 페이지네이션이 적용된 거래 정보 목록.
     */
    @Operation(summary = "실거래가 조건별 조회 (페이지네이션)", description = "조건에 맞는 실거래가 정보를 페이지 단위로 조회합니다")
    @GetMapping("/type/{buildingType}/deals")
    public BaseResponse<CursorPage<DealInfoResponseDto>> getDealInfoByFilterWithPagination(
                    @Parameter(description = "건물 유형 (apartment, villa, officetel)",
                                    required = true) @PathVariable String buildingType,
                    @Parameter(description = "거래 유형 (전세, 매매, 월세)") @RequestParam(
                                    required = false) String contractType,
                    @Parameter(description = "시군구 코드") @RequestParam(required = false) String sgg,
                    @Parameter(description = "읍면동 코드") @RequestParam(required = false) String emd,
                    @ParameterObject PageRequestDto requestDto) {
        return new BaseResponse<>((CursorPage<DealInfoResponseDto>) dealInfoService
                        .getDealInfoByFilterWithPagination(buildingType, contractType, sgg, emd,
                                        requestDto));

    }

    /**
     * 실거래가 house_info_id로 조회 (오프셋 페이지네이션).
     *
     * @param id house_info_id - 필수
     * @param buildingType 건물 유형 (apartment, villa, officetel) - 필수
     * @param requestDto 페이지 요청 정보
     * @return 오프셋 페이지네이션이 적용된 거래 정보 목록.
     */
    @Operation(summary = "실거래가 ID별 조회 (페이지네이션)",
                    description = "특정 house_info_id에 해당하는 실거래가 정보를 페이지 단위로 조회합니다")
    @GetMapping("/{id}/type/{buildingType}/deals")
    public BaseResponse<CursorPage<DealInfoResponseDto>> getDealInfoByHouseInfoIdWithPagination(
                    @Parameter(description = "house_info_id") @PathVariable String id,
                    @Parameter(description = "건물 유형 (apartment, villa, officetel)") @PathVariable String buildingType,
                    @ParameterObject PageRequestDto requestDto) {
        return new BaseResponse<>(dealInfoService
                        .getDealInfoByHouseInfoIdWithPagination(buildingType, id, requestDto));
    }
}
