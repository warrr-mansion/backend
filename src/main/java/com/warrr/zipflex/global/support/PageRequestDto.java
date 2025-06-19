package com.warrr.zipflex.global.support;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class PageRequestDto {

    private int pageNo;
    private int pageSize;

    @JsonIgnore
    public int getOffset() {
        return (pageNo - 1) * pageSize;
    }
    
    @JsonIgnore
    public int getLimitPlusOne() {
        return pageSize + 1;
    }
    
    public static PageRequestDto toDto(int pageNo, int pageSize) {
        return PageRequestDto.builder()
                        .pageNo(pageNo)
                        .pageSize(pageSize)
                        .build();
    }
    
}
