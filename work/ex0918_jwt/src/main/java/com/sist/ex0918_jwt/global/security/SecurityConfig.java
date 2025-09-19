package com.sist.ex0918_jwt.global.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll()).csrf(csrf -> csrf.disable());
        // http.csrf(AbstractHttpConfigurer::disable)
        // .headers(headers -> headers.frameOptions(
        //     HeadersConfigurer.FrameOptionsConfig::sameOrigin
        // )).authorizeHttpRequests(
        //     authorize -> authorize.requestMatchers("/**", "/reg", "/login", "/logout").permitAll()
        //     .anyRequest().authenticated()
        // );

        // http.csrf()는 보안 기능을 비활성화 하는 설정이다(Cross Site Request Forgery)
        // headers().frameOptions().sameOrigin() : 동일한 경로(출처)에서는 iframe으로 페이지를 열 수 있도록 허용
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
