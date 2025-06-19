package com.warrr.zipflex.api.notice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.warrr.zipflex.api.notice.dto.out.NoticeInfoResponseDto;
import com.warrr.zipflex.api.notice.service.NoticeService;
import com.warrr.zipflex.global.response.BaseResponse;
import com.warrr.zipflex.global.support.CursorPage;
import com.warrr.zipflex.global.support.PageRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Notice")
@RestController
@RequestMapping("/v1/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @Operation(summary = "공지사항 목록 조회 (페이지네이션)")
    @GetMapping
    public BaseResponse<CursorPage<NoticeInfoResponseDto>> list(
                    @RequestParam(defaultValue = "1") int pageNo,
                    @RequestParam(defaultValue = "10") int pageSize) {

        return new BaseResponse<>(
                        noticeService.getNoticesByPage(PageRequestDto.toDto(pageNo, pageSize)));
    }

    @Operation(summary = "공지사항 상세 조회")
    @GetMapping("/{id}")
    public BaseResponse<NoticeInfoResponseDto> detail(@PathVariable Long id) {
        return new BaseResponse<>(noticeService.getNotice(id));
    }
    
}
