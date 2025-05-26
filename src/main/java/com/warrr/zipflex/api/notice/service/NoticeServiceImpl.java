package com.warrr.zipflex.api.notice.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.warrr.zipflex.api.notice.dao.NoticeDao;
import com.warrr.zipflex.api.notice.dto.in.NoticeCreateRequestDto;
import com.warrr.zipflex.api.notice.dto.in.NoticeUpdateRequestDto;
import com.warrr.zipflex.api.notice.dto.out.NoticeInfoResponseDto;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeDao noticeDao;

    @Override
    public void createNotice(NoticeCreateRequestDto requestDto) {
        noticeDao.insertNotice(requestDto);
    }

    @Transactional(readOnly = true)
    @Override
    public List<NoticeInfoResponseDto> getAllNotices() {
        return noticeDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public NoticeInfoResponseDto getNotice(Long id) {
        return noticeDao.findById(id);
    }

    @Override
    public void updateNotice(NoticeUpdateRequestDto requestDto) {
        noticeDao.updateNotice(requestDto);
    }

    @Override
    public void deleteNotice(Long id) {
        noticeDao.deleteNotice(id);
    }
    
}
