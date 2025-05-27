package com.warrr.zipflex.api.auth.dto.in;

import java.time.LocalDateTime;
import java.util.UUID;
import com.warrr.zipflex.api.auth.vo.in.SignUpRequestVo;
import com.warrr.zipflex.api.member.domain.model.Role;
import com.warrr.zipflex.api.member.domain.model.State;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequestDto {

    private String memberUuid;
    private String nickname;
    private String email;
    private String password;
    private State state;
    private Role role;
    private LocalDateTime registDate;
    private LocalDateTime modifyDate;

    @Builder
    public SignUpRequestDto(String memberUuid, String nickname, String email, String password,
                    State state, Role role, LocalDateTime registDate, LocalDateTime modifyDate) {

        this.memberUuid = memberUuid;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.state = state;
        this.role = role;
        this.registDate = registDate;
        this.modifyDate = modifyDate;
    }

    public static SignUpRequestDto toDto(SignUpRequestVo requestVo, String encodedPassword) {
        LocalDateTime now = LocalDateTime.now();
        
        // TODO: Role, State 확장 SignUp
        return SignUpRequestDto.builder()
                        .memberUuid(UUID.randomUUID().toString())
                        .nickname(requestVo.getNickname())
                        .email(requestVo.getEmail())
                        .password(encodedPassword)
                        .state(State.ACTIVATION)
                        .role(Role.VISITOR)
                        .registDate(now)
                        .modifyDate(now)
                        .build();
    }

}
