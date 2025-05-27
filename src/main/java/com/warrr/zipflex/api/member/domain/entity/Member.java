package com.warrr.zipflex.api.member.domain.entity;

import com.warrr.zipflex.api.member.domain.model.Role;
import com.warrr.zipflex.api.member.domain.model.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private String memberUuid;
    private String nickname;
    private String email;
    private String password;
    private State state;
    private Role role;
    
}
