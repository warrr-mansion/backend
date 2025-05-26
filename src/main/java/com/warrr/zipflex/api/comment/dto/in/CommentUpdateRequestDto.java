package com.warrr.zipflex.api.comment.dto.in;

import com.warrr.zipflex.api.comment.vo.in.CommentUpdateRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentUpdateRequestDto {

    private Long commentId;
    private String memberUuid;
    private String content;

    @Builder
    public CommentUpdateRequestDto(Long commentId, String content, String memberUuid) {
        this.commentId = commentId;
        this.content = content;
        this.memberUuid = memberUuid;
    }

    public static CommentUpdateRequestDto toDto(Long commentId,
                    CommentUpdateRequestVo requestVo, String memberUuid) {
        
        return CommentUpdateRequestDto.builder()
                        .commentId(commentId)
                        .content(requestVo.getContent())
                        .memberUuid(memberUuid)
                        .build();                     
    }

}
