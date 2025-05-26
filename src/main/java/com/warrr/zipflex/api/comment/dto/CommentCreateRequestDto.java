package com.warrr.zipflex.api.comment.dto;

import com.warrr.zipflex.api.comment.vo.in.CommentCreateRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentCreateRequestDto {

    private Long houseInfoId;
    private String memberUuid;
    private String nickname;
    private String content;

    @Builder
    public CommentCreateRequestDto(String memberUuid, Long houseInfoId, String nickname,
                    String content) {
        this.memberUuid = memberUuid;
        this.houseInfoId = houseInfoId;
        this.nickname = nickname;
        this.content = content;
    }

    public static CommentCreateRequestDto toDto(CommentCreateRequestVo requestVo, String memberUuid,
                    String nickname) {
        
        return CommentCreateRequestDto.builder()
                        .houseInfoId(requestVo.getHouseInfoId())
                        .memberUuid(memberUuid)
                        .nickname(nickname)
                        .content(requestVo.getContent())
                        .build();
    }

}
