package com.warrr.zipflex.api.notice.controller;

import com.warrr.zipflex.api.notice.dto.in.NoticeCreateRequestDto;
import com.warrr.zipflex.api.notice.dto.in.NoticeUpdateRequestDto;
import com.warrr.zipflex.api.notice.dto.out.NoticeInfoResponseDto;
import com.warrr.zipflex.api.notice.service.NoticeService;
import com.warrr.zipflex.global.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping
    public BaseResponse<Void> create(@RequestBody NoticeCreateRequestDto dto) {
        noticeService.createNotice(dto);
        return new BaseResponse<>();
    }

    @GetMapping
    public BaseResponse<List<NoticeInfoResponseDto>> list() {
        return new BaseResponse<>(noticeService.getAllNotices());
    }

    @GetMapping("/{id}")
    public BaseResponse<NoticeInfoResponseDto> detail(@PathVariable Long id) {
        return new BaseResponse<>(noticeService.getNotice(id));
    }

    @PutMapping
    public BaseResponse<Void> update(@RequestBody NoticeUpdateRequestDto dto) {
        noticeService.updateNotice(dto);
        return new BaseResponse<>();
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> delete(@PathVariable Long id) {
        noticeService.deleteNotice(id);
        return new BaseResponse<>();
    }
}
