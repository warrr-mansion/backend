package com.warrr.zipflex.api.auth.domain.model;

import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.warrr.zipflex.api.member.domain.model.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Authority {

    USER("ROLE_USER"), 
    SELLER("ROLE_SELLER"), 
    ADMIN("ROLE_ADMIN");

    private final String authority;

    public static List<SimpleGrantedAuthority> getAuthorities(Role role) {
        return switch (role) {
            case ADMIN -> List.of(new SimpleGrantedAuthority(ADMIN.toString()),
                            new SimpleGrantedAuthority(USER.toString()));
            case SELLER -> List.of(new SimpleGrantedAuthority(SELLER.toString()),
                            new SimpleGrantedAuthority(USER.toString()));
            case VISITOR -> List.of(new SimpleGrantedAuthority(USER.toString()));
        };
    }

}
