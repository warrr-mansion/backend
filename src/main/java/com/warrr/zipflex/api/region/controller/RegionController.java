package com.warrr.zipflex.api.region.controller;

import com.warrr.zipflex.api.region.dto.DongDto;
import com.warrr.zipflex.api.region.dto.GugunDto;
import com.warrr.zipflex.api.region.dto.SidoDto;
import com.warrr.zipflex.api.region.service.RegionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Region")
@RestController
@RequestMapping("/v1/regions")
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService;

    @Operation(summary = "시·도 목록 조회")
    @GetMapping("/sido")
    public List<SidoDto> getSido() {
        return regionService.getAllSido();
    }

    @Operation(summary = "구·군 목록 조회")
    @GetMapping("/sido/{sidoCode}/gugun")
    public List<GugunDto> getGugun(@PathVariable String sidoCode) {
        return regionService.getAllGugun(sidoCode);
    }

    @Operation(summary = "동 목록 조회")
    @GetMapping("/gugun/{gugunCode}/dong")
    public List<DongDto> getDong(@PathVariable String gugunCode) {
        return regionService.getAllDong(gugunCode);
    }
}
