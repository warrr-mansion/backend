package com.warrr.zipflex.api.notice.service;

import com.warrr.zipflex.api.notice.dto.in.NoticeCreateRequestDto;
import com.warrr.zipflex.api.notice.dto.in.NoticeUpdateRequestDto;
import com.warrr.zipflex.api.notice.dto.out.NoticeInfoResponseDto;
import com.warrr.zipflex.global.support.CursorPage;
import com.warrr.zipflex.global.support.PageRequestDto;

public interface NoticeService {

    void createNotice(NoticeCreateRequestDto requestDto);
    
    CursorPage<NoticeInfoResponseDto> getNoticesByPage(PageRequestDto requestDto);
    
    NoticeInfoResponseDto getNotice(Long id);
    
    void updateNotice(NoticeUpdateRequestDto requestDto);
    
    void deleteNotice(Long id);
    
}
