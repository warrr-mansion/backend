package com.warrr.zipflex.api.notice.dto.in;

import com.warrr.zipflex.api.notice.vo.in.NoticeUpdateRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class NoticeUpdateRequestDto {

    private Long id;
    private String title;
    private String content;
    
    @Builder
    public NoticeUpdateRequestDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
    
    public static NoticeUpdateRequestDto toDto(Long id, NoticeUpdateRequestVo requestVo) {
        return NoticeUpdateRequestDto.builder()
                        .id(id)
                        .title(requestVo.getTitle())
                        .content(requestVo.getContent())
                        .build();
    }
    
}
