package com.warrr.zipflex.api.auth.vo.out;

import com.warrr.zipflex.api.member.domain.model.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInResponseVo {

    private String uuid;
    private Role role;
    
    @Builder
    public SignInResponseVo(String uuid, Role role) {
        this.uuid = uuid;
        this.role = role;
    }
    
}
