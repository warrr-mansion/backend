package com.warrr.zipflex.api.member.dto.in;

import com.warrr.zipflex.api.member.vo.in.PasswordUpdateRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PasswordUpdateRequestDto {

    private String currentPassword;
    private String newPassword;
    
    @Builder
    public PasswordUpdateRequestDto(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }
    
    public static PasswordUpdateRequestDto toDto(PasswordUpdateRequestVo requestVo) {
        return PasswordUpdateRequestDto.builder()
                        .currentPassword(requestVo.getCurrentPassword())
                        .newPassword(requestVo.getNewPassword())
                        .build();
    }
    
}
