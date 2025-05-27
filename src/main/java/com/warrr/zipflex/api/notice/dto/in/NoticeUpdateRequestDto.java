package com.warrr.zipflex.api.notice.dto.in;

import com.warrr.zipflex.api.notice.vo.in.NoticeUpdateRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class NoticeUpdateRequestDto {

    private Long noticeId;
    private String title;
    private String content;
    
    @Builder
    public NoticeUpdateRequestDto(Long noticeId, String title, String content) {
        this.noticeId = noticeId;
        this.title = title;
        this.content = content;
    }
    
    public static NoticeUpdateRequestDto toDto(Long noticeId, NoticeUpdateRequestVo requestVo) {
        return NoticeUpdateRequestDto.builder()
                        .noticeId(noticeId)
                        .title(requestVo.getTitle())
                        .content(requestVo.getContent())
                        .build();
    }
    
}
