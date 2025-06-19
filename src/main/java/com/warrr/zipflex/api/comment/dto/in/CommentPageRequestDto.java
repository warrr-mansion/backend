package com.warrr.zipflex.api.comment.dto.in;

import com.warrr.zipflex.global.support.PageRequestDto;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@SuperBuilder
public class CommentPageRequestDto extends PageRequestDto {

    private Long houseInfoId;
    
    public static CommentPageRequestDto toDto(Long houseInfoId, int pageNo, int pageSize) {
        return CommentPageRequestDto.builder()
                        .houseInfoId(houseInfoId)
                        .pageNo(pageNo)
                        .pageSize(pageSize)
                        .build();
    }
    
}
