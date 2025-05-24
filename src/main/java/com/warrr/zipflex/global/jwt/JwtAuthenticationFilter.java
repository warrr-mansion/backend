package com.warrr.zipflex.global.jwt;

import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.warrr.zipflex.global.jwt.properties.JwtProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProperties jwtProperties;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader(jwtProperties.getAccessTokenPrefix());

        if (authHeader == null || !authHeader.startsWith(jwtProperties.getTokenPrefix())) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = getAccessToken(authHeader);
        boolean isValidToken = jwtTokenProvider.isValidToken(token);
        boolean isAuthenticated = SecurityContextHolder.getContext().getAuthentication() != null;

        // 토큰이 유효하고 인증되어 있지 않다면, 토큰을 이용해 인증 객체 생성 => SecurityContext에 저장
        if (isValidToken && !isAuthenticated) {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String getAccessToken(String requestHeader) {
        String prefix = jwtProperties.getTokenPrefix();

        if (requestHeader != null && requestHeader.startsWith(prefix)) {
            return requestHeader.substring(prefix.length()).trim();
        }

        return null;
    }
}
