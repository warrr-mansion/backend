package com.warrr.zipflex.api.auth.dto.out;

import com.warrr.zipflex.api.auth.vo.out.SignInResponseVo;
import com.warrr.zipflex.api.member.domain.model.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class JwtTokenResponseDto {
    
    private String accessToken;
    private String refreshToken;
    private String uuid;
    private Role role;
    
    @Builder
    public JwtTokenResponseDto(String accessToken, String refreshToken, String uuid, Role role) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.uuid = uuid;
        this.role = role;
    }
    
    public SignInResponseVo toVo() {
        return SignInResponseVo.builder()
                        .uuid(uuid)
                        .role(role)
                        .build();
    }
    
}
