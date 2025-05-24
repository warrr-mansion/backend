package com.warrr.zipflex.api.auth.dto.out;

import com.warrr.zipflex.api.auth.vo.out.SignInResponseVo;
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
    
    @Builder
    public JwtTokenResponseDto(String accessToken, String refreshToken, String uuid) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.uuid = uuid;
    }
    
    public SignInResponseVo toVo() {
        return SignInResponseVo.builder()
                        .uuid(uuid)
                        .build();
    }
    
}
