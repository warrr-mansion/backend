package com.warrr.zipflex.api.auth.domain.model;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.warrr.zipflex.api.member.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class AuthUserDetail implements UserDetails {

    private String uuid;
    private String password;
    private String email;
    private String nickname;
    private Collection<? extends GrantedAuthority> authorities;

    @Builder
    public AuthUserDetail(String uuid, String password, String email, String nickname,
                    Collection<? extends GrantedAuthority> authorities) {
        
        this.uuid = uuid;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.authorities = authorities;
    }
    
    public static AuthUserDetail fromMember(Member member, List<SimpleGrantedAuthority> authorities) {
        return AuthUserDetail.builder()
            .uuid(member.getMemberUuid())
            .password(member.getPassword())
            .email(member.getEmail())
            .nickname(member.getNickname())
            .authorities(authorities)
            .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.uuid;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
