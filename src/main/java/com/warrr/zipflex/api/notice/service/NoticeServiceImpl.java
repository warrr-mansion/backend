package com.warrr.zipflex.api.notice.service;

import static com.warrr.zipflex.global.response.BaseResponseStatus.NO_EXIST_NOTICE;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.warrr.zipflex.api.notice.dao.NoticeDao;
import com.warrr.zipflex.api.notice.dto.in.NoticeCreateRequestDto;
import com.warrr.zipflex.api.notice.dto.in.NoticeUpdateRequestDto;
import com.warrr.zipflex.api.notice.dto.out.NoticeInfoResponseDto;
import com.warrr.zipflex.global.exception.BaseException;
import com.warrr.zipflex.global.support.CursorPage;
import com.warrr.zipflex.global.support.PageRequestDto;
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
    public CursorPage<NoticeInfoResponseDto> getNoticesByPage(PageRequestDto requestDto) {
        List<NoticeInfoResponseDto> rawList = noticeDao.findAllByPage(requestDto);

        boolean hasNext = rawList.size() > requestDto.getPageSize();
        List<NoticeInfoResponseDto> content =
                        hasNext ? rawList.subList(0, requestDto.getPageSize()) : rawList;
        Long nextCursor = hasNext ? content.get(content.size() - 1).getId() : null;

        return CursorPage.<NoticeInfoResponseDto>builder().content(content).hasNext(hasNext)
                        .nextCursor(nextCursor).pageSize(content.size())
                        .pageNo(requestDto.getPageNo()).build();
    }

    @Transactional(readOnly = true)
    @Override
    public NoticeInfoResponseDto getNotice(Long noticeId) {
        return Optional.ofNullable(noticeDao.findById(noticeId))
                        .orElseThrow(() -> new BaseException(NO_EXIST_NOTICE));
    }

    @Override
    public void updateNotice(NoticeUpdateRequestDto requestDto) {
        noticeDao.updateNotice(requestDto);
    }

    @Override
    public void deleteNotice(Long noticeId) {
        noticeDao.deleteNotice(noticeId);
    }

}
