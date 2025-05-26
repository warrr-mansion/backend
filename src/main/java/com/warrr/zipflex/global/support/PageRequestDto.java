package com.warrr.zipflex.global.support;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDto {
    
    @Schema(defaultValue = "1", description = "조회할 페이지 번호 (1부터 시작)")
    @Builder.Default
    private int pageNo = 1;

    @Schema(defaultValue = "10", description = "페이지당 항목 수")
    @Builder.Default
    private int pageSize = 10;

    @JsonIgnore
    public int getOffset() {
        return (pageNo - 1) * pageSize;
    }
    
    @JsonIgnore
    public int getLimitPlusOne() {
        return pageSize + 1;
    }
    
}
