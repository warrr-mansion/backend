package com.warrr.zipflex.api.member.dto.out;

import com.warrr.zipflex.api.member.domain.model.Role;
import com.warrr.zipflex.api.member.domain.model.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDto {
    
    private String memberUuid;
    private String nickname;
    private String email;
    private State state;
    private Role role;
    
}
