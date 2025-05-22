package com.warrr.zipflex.api.auth.service;

import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.warrr.zipflex.api.auth.domain.model.AuthUserDetail;
import com.warrr.zipflex.api.member.dao.MemberDao;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthUserDetailService implements UserDetailsService {

    private final MemberDao memberDao;

    @Override
    public UserDetails loadUserByUsername(String uuid) throws UsernameNotFoundException {

        return new AuthUserDetail(Optional.ofNullable(memberDao.findByUuid(uuid))
                        .orElseThrow(() -> new UsernameNotFoundException(uuid)));
    }

}
