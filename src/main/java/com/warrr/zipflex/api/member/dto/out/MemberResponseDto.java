package com.warrr.zipflex.api.member.dto.out;

import com.warrr.zipflex.api.member.domain.entity.Member;
import com.warrr.zipflex.api.member.domain.model.Role;
import com.warrr.zipflex.api.member.domain.model.State;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDto {

    private String memberUuid;
    private String nickname;
    private String email;
    private State state;
    private Role role;

    @Builder
    public MemberResponseDto(String memberUuid, String nickname, String email, State state,
                    Role role) {

        this.memberUuid = memberUuid;
        this.nickname = nickname;
        this.email = email;
        this.state = state;
        this.role = role;
    }

    public static MemberResponseDto fromEntity(Member member) {
        return MemberResponseDto.builder()
                        .memberUuid(member.getMemberUuid())
                        .nickname(member.getNickname())
                        .email(member.getEmail())
                        .state(member.getState())
                        .role(member.getRole())
                        .build();
    }

}
