package com.warrr.zipflex.api.notice.dao;

import com.warrr.zipflex.api.notice.dto.in.NoticeCreateRequestDto;
import com.warrr.zipflex.api.notice.dto.in.NoticeUpdateRequestDto;
import com.warrr.zipflex.api.notice.dto.out.NoticeInfoResponseDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface NoticeDao {

    void insertNotice(NoticeCreateRequestDto dto);

    List<NoticeInfoResponseDto> findAll();

    NoticeInfoResponseDto findById(Long id);

    void updateNotice(NoticeUpdateRequestDto dto);

    void deleteNotice(Long id);

    // ⚠ view_count 컬럼이 실제 DB에 존재할 경우에만 유지
    // void increaseViewCount(Long id);
}
