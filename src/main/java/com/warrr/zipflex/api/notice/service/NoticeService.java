package com.warrr.zipflex.api.notice.service;

import com.warrr.zipflex.api.notice.dao.NoticeDao;
import com.warrr.zipflex.api.notice.dto.in.NoticeCreateRequestDto;
import com.warrr.zipflex.api.notice.dto.in.NoticeUpdateRequestDto;
import com.warrr.zipflex.api.notice.dto.out.NoticeInfoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeDao noticeDao;

    public void createNotice(NoticeCreateRequestDto dto) {
        Map<String, Object> param = new HashMap<>();
        param.put("title", dto.getTitle());
        param.put("content", dto.getContent());
        param.put("registDate", LocalDateTime.now());

        noticeDao.insertNotice(param);
    }

    public List<NoticeInfoResponseDto> getAllNotices() {
        return noticeDao.findAll();
    }

    public NoticeInfoResponseDto getNotice(Long id) {
        // noticeDao.increaseViewCount(id); // 옵션: 조회수 컬럼 있을 때만 유지
        return noticeDao.findById(id);
    }

    public void updateNotice(NoticeUpdateRequestDto dto) {
        noticeDao.updateNotice(dto);
    }

    public void deleteNotice(Long id) {
        noticeDao.deleteNotice(id);
    }
}
