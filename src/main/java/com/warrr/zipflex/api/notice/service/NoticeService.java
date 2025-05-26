package com.warrr.zipflex.api.notice.service;

import java.util.List;
import com.warrr.zipflex.api.notice.dto.in.NoticeCreateRequestDto;
import com.warrr.zipflex.api.notice.dto.in.NoticeUpdateRequestDto;
import com.warrr.zipflex.api.notice.dto.out.NoticeInfoResponseDto;

public interface NoticeService {

    void createNotice(NoticeCreateRequestDto requestDto);
    
    List<NoticeInfoResponseDto> getAllNotices();
    
    NoticeInfoResponseDto getNotice(Long id);
    
    void updateNotice(Long id, NoticeUpdateRequestDto requestDto);
    
    void deleteNotice(Long id);
    
}
