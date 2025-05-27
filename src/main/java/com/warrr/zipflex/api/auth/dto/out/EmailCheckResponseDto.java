package com.warrr.zipflex.api.auth.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EmailCheckResponseDto {

    private boolean isDuplicated;
    
}
