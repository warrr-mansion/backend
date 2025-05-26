package com.warrr.zipflex.api.notice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.warrr.zipflex.api.notice.dto.in.NoticeCreateRequestDto;
import com.warrr.zipflex.api.notice.dto.in.NoticeUpdateRequestDto;
import com.warrr.zipflex.api.notice.service.NoticeService;
import com.warrr.zipflex.api.notice.vo.in.NoticeUpdateRequestVo;
import com.warrr.zipflex.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Notice - Admin Only")
@RequestMapping("/v1/admin/notices")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
@RestController
public class AdminNoticeController {

    private final NoticeService noticeService;

    @Operation(summary = "공지사항 작성 (Admin Only)")
    @PostMapping
    public BaseResponse<Void> create(@RequestBody NoticeCreateRequestDto requestDto) {
        noticeService.createNotice(requestDto);
        return new BaseResponse<>();
    }

    @Operation(summary = "공지사항 수정 (Admin Only)")
    @PutMapping("/{id}")
    public BaseResponse<Void> update(@PathVariable Long id,
                    @RequestBody NoticeUpdateRequestVo requestVo) {
        
        noticeService.updateNotice(NoticeUpdateRequestDto.toDto(id, requestVo));
        return new BaseResponse<>();
    }

    @Operation(summary = "공지사항 삭제 (Admin Only)")
    @DeleteMapping("/{id}")
    public BaseResponse<Void> delete(@PathVariable Long id) {
        noticeService.deleteNotice(id);
        return new BaseResponse<>();
    }

}
