package com.warrr.zipflex.api.notice.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.warrr.zipflex.api.notice.dto.in.NoticeCreateRequestDto;
import com.warrr.zipflex.api.notice.dto.in.NoticeUpdateRequestDto;
import com.warrr.zipflex.api.notice.dto.out.NoticeInfoResponseDto;

@Mapper
public interface NoticeDao {

    void insertNotice(NoticeCreateRequestDto requestDto);

    List<NoticeInfoResponseDto> findAll();

    NoticeInfoResponseDto findById(Long id);

    void updateNotice(NoticeUpdateRequestDto requestDto);

    void deleteNotice(Long id);
    
}
