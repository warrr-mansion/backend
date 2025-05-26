package com.warrr.zipflex.api.notice.service;

import com.warrr.zipflex.api.notice.dao.NoticeDao;
import com.warrr.zipflex.api.notice.dto.in.NoticeCreateRequestDto;
import com.warrr.zipflex.api.notice.dto.in.NoticeUpdateRequestDto;
import com.warrr.zipflex.api.notice.dto.out.NoticeInfoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeDao noticeDao;

    @Override
    public void createNotice(NoticeCreateRequestDto requestDto) {
        Map<String, Object> param = new HashMap<>();
        param.put("title", requestDto.getTitle());
        param.put("content", requestDto.getContent());
        param.put("registDate", LocalDateTime.now());

        noticeDao.insertNotice(param);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoticeInfoResponseDto> getAllNotices() {
        return noticeDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public NoticeInfoResponseDto getNotice(Long id) {
        // noticeDao.increaseViewCount(id); // 옵션: 조회수 컬럼 있을 때만 유지
        return noticeDao.findById(id);
    }

    @Override
    public void updateNotice(Long id, NoticeUpdateRequestDto requestDto) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", id); // 또는 param.put("id", dto.getId());
        param.put("title", requestDto.getTitle());
        param.put("content", requestDto.getContent());
        param.put("registDate", LocalDateTime.now());

        noticeDao.updateNotice(param);
    }

    @Override
    public void deleteNotice(Long id) {
        noticeDao.deleteNotice(id);
    }
    
}
