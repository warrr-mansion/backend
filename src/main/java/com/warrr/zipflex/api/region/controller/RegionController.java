package com.warrr.zipflex.api.region.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.warrr.zipflex.api.region.dto.DongDto;
import com.warrr.zipflex.api.region.dto.GugunDto;
import com.warrr.zipflex.api.region.dto.SidoDto;
import com.warrr.zipflex.api.region.service.RegionService;
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

    @GetMapping("/gugun")
    public List<GugunDto> getGugun(@RequestParam String sidoCode) {
        return regionService.getAllGugun(sidoCode);
    }

    @GetMapping("/dong")
    public List<DongDto> getDong(@RequestParam String gugunCode) {
        return regionService.getAllDong(gugunCode);
    }
}
