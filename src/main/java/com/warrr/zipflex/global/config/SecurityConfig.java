package com.warrr.zipflex.global.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import com.warrr.zipflex.global.exception.handler.AuthenticationEntryPointHandler;
import com.warrr.zipflex.global.jwt.JwtAuthenticationFilter;
import com.warrr.zipflex.global.jwt.properties.CorsProperties;
import com.warrr.zipflex.global.jwt.properties.JwtProperties;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // CORS 설정, 인증 처리, JWT 인증 필터 설정
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAutenticationFilter;
    private final AuthenticationEntryPointHandler authenticationEntryPointHandler;

    private final JwtProperties jwtProperties;
    private final CorsProperties corsProperties;

    @Bean
    CorsFilter corsFilter() {

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);

        config.setAllowedOrigins(corsProperties.getAllowedOrigins());
        // config.addAllowedOriginPattern("*"); // 모든 Origin 허용 (for 테스트)
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setExposedHeaders(List.of(jwtProperties.getAccessTokenPrefix()));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);    // 모든 URL에 대해 CORS 설정 적용
        return new CorsFilter(source);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors(Customizer.withDefaults()).csrf(AbstractHttpConfigurer::disable)
                        .formLogin(AbstractHttpConfigurer::disable)
                        .httpBasic(AbstractHttpConfigurer::disable)
                        .logout(AbstractHttpConfigurer::disable)

                        .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                                        // 인증 없이 가능한 URL + 메서드
                                        .requestMatchers(HttpMethod.GET, "/v1/review").permitAll()
                                        // 인증이 필요한 URL + 메서드
                                        .requestMatchers(HttpMethod.POST, "/v1/review")
                                        .authenticated()
                                        .requestMatchers(HttpMethod.PUT, "/v1/review")
                                        .authenticated()
                                        .requestMatchers(HttpMethod.DELETE, "/v1/review")
                                        .authenticated()
                                        // 인증이 필요한 URL 설정

                                        .requestMatchers("/v1/mypage/**", "/v1/like/**")
                                        .authenticated().anyRequest().permitAll())
                        .sessionManagement(sessionManagement -> sessionManagement
                                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .exceptionHandling(exceptionHandling -> exceptionHandling
                                        .authenticationEntryPoint(authenticationEntryPointHandler))
                        .authenticationProvider(authenticationProvider)
                        .addFilterBefore(jwtAutenticationFilter,
                                        UsernamePasswordAuthenticationFilter.class)
                        .addFilter(corsFilter());

        return http.build();
    }

}
