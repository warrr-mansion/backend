package com.warrr.zipflex.api.notice.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.warrr.zipflex.api.notice.dto.out.NoticeInfoResponseDto;
import com.warrr.zipflex.api.notice.service.NoticeService;
import com.warrr.zipflex.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Notice")
@RestController
@RequestMapping("/v1/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    public BaseResponse<List<NoticeInfoResponseDto>> list() {
        return new BaseResponse<>(noticeService.getAllNotices());
    }

    @GetMapping("/{id}")
    public BaseResponse<NoticeInfoResponseDto> detail(@PathVariable Long id) {
        return new BaseResponse<>(noticeService.getNotice(id));
    }
    
}
