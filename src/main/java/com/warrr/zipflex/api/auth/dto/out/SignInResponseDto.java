package com.warrr.zipflex.api.auth.dto.out;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInResponseDto {

    private String memberUuid;
    private String email;
    private String password;
    
}
