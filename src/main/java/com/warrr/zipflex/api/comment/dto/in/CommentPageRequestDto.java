package com.warrr.zipflex.api.comment.dto.in;

import com.warrr.zipflex.global.support.PageRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class CommentPageRequestDto extends PageRequestDto {

    private Long houseInfoId;

    @Builder
    public CommentPageRequestDto(int pageNo, int pageSize, Long houseInfoId) {
        super(pageNo, pageSize);
        this.houseInfoId = houseInfoId;
    }
    
    public static CommentPageRequestDto toDto(Long houseInfoId, int pageNo, int pageSize) {
        return CommentPageRequestDto.builder()
                        .houseInfoId(houseInfoId)
                        .pageNo(pageNo)
                        .pageSize(pageSize)
                        .build();
    }
    
}
