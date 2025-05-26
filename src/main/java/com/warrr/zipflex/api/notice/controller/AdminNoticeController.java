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
import com.warrr.zipflex.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Notice - Admin Only")
@RequestMapping("/v1/admin/notices")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
@RestController
public class AdminNoticeController {

    private final NoticeService noticeService;

    @PostMapping
    public BaseResponse<Void> create(@RequestBody NoticeCreateRequestDto dto) {
        noticeService.createNotice(dto);
        return new BaseResponse<>();
    }

    @PutMapping("/{id}")
    public BaseResponse<Void> update(@PathVariable Long id,
                    @RequestBody NoticeUpdateRequestDto dto) {
        
        noticeService.updateNotice(id, dto);
        return new BaseResponse<>();
    }


    @DeleteMapping("/{id}")
    public BaseResponse<Void> delete(@PathVariable Long id) {
        noticeService.deleteNotice(id);
        return new BaseResponse<>();
    }

}
