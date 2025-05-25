package com.warrr.zipflex.api.member.dto.out;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PasswordCheckResponseDto {

    private boolean isMatched;
    
}
