package com.warrr.zipflex.api.ai.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class CommentSummaryResponseDto {

    private String summary;

    @Builder
    public CommentSummaryResponseDto(String summary) {
        this.summary = summary;
    }
    
    public static CommentSummaryResponseDto toDto(String summary) {
        return CommentSummaryResponseDto.builder()
                        .summary(summary)
                        .build();
    }
    
}
