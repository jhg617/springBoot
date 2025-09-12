package com.sist.ex0911_jwt.config;

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
public class SecurityJavaConfig {

    private final PasswordEncoder aPasswordEncoder;

    SecurityJavaConfig(PasswordEncoder aPasswordEncoder) {
        this.aPasswordEncoder = aPasswordEncoder;
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
            .csrf(csrf -> csrf.disable());
        return http.build(); //모든 요청 허용

        /* 
        http.csrf(AbstractHttpConfigurer::disable)
            .headers(headers -> headers.frameOptions(
                HeadersConfigurer.FrameOptionsConfig::sameOrigin
            )).authorizeHttpRequests(
                authorize -> authorize.requestMatchers("/**", "/reg",
                "/login", "/logout").permitAll()
                //명시된 URL 패턴(/**, /reg, /login, /logout)에 대해서는
                // 인증(로그인) 여부와 상관없이 모든 사용자의 접근을 허용한다

                //.anyRequest().authenticated()
            ); //람다식(인자 전달)

        // http.csrf()는 보안 기능을 비활성화 하는 설정이다.(Cross Site Request Forgery)
        // headers().frameOptions().sameOrigin()는
        //  동일한 경로(출처)에서는 iframe으로 페이지를 열 수 있도록 허용
        return http.build(); 
        */
    }

    @Bean
    public static PasswordEncoder aPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
