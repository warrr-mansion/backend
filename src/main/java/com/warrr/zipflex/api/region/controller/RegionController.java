package com.warrr.zipflex.api.region.controller;

import com.warrr.zipflex.api.region.dto.DongDto;
import com.warrr.zipflex.api.region.dto.GugunDto;
import com.warrr.zipflex.api.region.dto.SidoDto;
import com.warrr.zipflex.api.region.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/region")
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService;

    @GetMapping("/sido")
    public List<SidoDto> getSido() {
        return regionService.getAllSido();
    }

    @GetMapping("/sido/{sidoCode}/gugun")
    public List<GugunDto> getGugun(@PathVariable String sidoCode) {
        return regionService.getAllGugun(sidoCode);
    }

    @GetMapping("/gugun/{gugunCode}/dong")
    public List<DongDto> getDong(@PathVariable String gugunCode) {
        return regionService.getAllDong(gugunCode);
    }
}
