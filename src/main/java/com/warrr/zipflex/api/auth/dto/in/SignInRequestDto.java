package com.warrr.zipflex.api.auth.dto.in;

import com.warrr.zipflex.api.auth.vo.in.SignInRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SignInRequestDto {

    private String email;
    private String password;
    
    @Builder
    public SignInRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    public static SignInRequestDto toDto(SignInRequestVo requestVo) {
        return SignInRequestDto.builder()
                        .email(requestVo.getEmail())
                        .password(requestVo.getPassword())
                        .build();
    }
    
}
