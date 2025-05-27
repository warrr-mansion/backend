package com.warrr.zipflex.api.comment.dto.out;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {

    private Long commentId;
    private Long houseInfoId;
    private String memberUuid;
    private String nickname;
    private String content;
    private LocalDateTime registDate;
    private LocalDateTime modifyDate;
    
    @Builder
    public CommentResponseDto(Long commentId, Long houseInfoId, String memberUuid, String nickname,
                    String content, LocalDateTime registDate, LocalDateTime modifyDate) {
        
        this.commentId = commentId;
        this.houseInfoId = houseInfoId;
        this.memberUuid = memberUuid;
        this.nickname = nickname;
        this.content = content;
        this.registDate = registDate;
        this.modifyDate = modifyDate;
    }
    
}
