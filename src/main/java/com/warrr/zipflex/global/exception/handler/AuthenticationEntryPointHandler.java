package com.warrr.zipflex.global.exception.handler;

import static com.warrr.zipflex.global.response.BaseResponseStatus.FAILED_TO_LOGIN;
import static com.warrr.zipflex.global.response.BaseResponseStatus.NO_EXIST_USER;
import static com.warrr.zipflex.global.response.BaseResponseStatus.DISABLED_USER;
import static com.warrr.zipflex.global.response.BaseResponseStatus.NO_SIGN_IN;
import static com.warrr.zipflex.global.response.BaseResponseStatus.NO_ACCESS_AUTHORITY;

import java.io.IOException;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.warrr.zipflex.global.response.BaseResponse;
import com.warrr.zipflex.global.response.BaseResponseStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                    AuthenticationException authException) throws IOException {
        log.error("Spring Security 인증 예외 발생: {}", authException.getMessage());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        BaseResponseStatus status = mapExceptionToStatus(authException);
        BaseResponse<Object> errorResponse = new BaseResponse<>(status);

        response.setStatus(status.getHttpStatus().value());
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }

    private BaseResponseStatus mapExceptionToStatus(AuthenticationException e) {
        if (e instanceof BadCredentialsException) {
            return FAILED_TO_LOGIN;
        } else if (e instanceof UsernameNotFoundException) {
            return NO_EXIST_USER;
        } else if (e instanceof AccountExpiredException || e instanceof DisabledException) {
            return DISABLED_USER;
        } else if (e instanceof CredentialsExpiredException) {
            return NO_SIGN_IN;
        } else if (e instanceof LockedException) {
            return NO_ACCESS_AUTHORITY;
        } else {
            return NO_SIGN_IN;
        }
    }
}

