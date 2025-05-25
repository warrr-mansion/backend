package com.warrr.zipflex.api.auth.dto.out;

import com.warrr.zipflex.api.member.domain.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInResponseDto {

    private String memberUuid;
    private String email;
    private String password;
    private Role role;
    
    public JwtTokenResponseDto toJwtResponse(String accessToken, String refreshToken) {
        return JwtTokenResponseDto.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .uuid(memberUuid)
                        .role(role)
                        .build();
                        
    }
    
}
