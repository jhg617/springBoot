package com.sist.ex0918_jwt.global.security;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter{

    @Override
    @SneakyThrows // try-catch로 예외처리를 해야 할 것을.. 명시적 예외처리를
                    // 생략할 수 있도록 해준다.
    protected void doFilterInternal(HttpServletRequest request,
                    HttpServletResponse response,
                    FilterChain filterChain) throws ServletException, IOException {
        if(request.getRequestURI().equals("/api/members/login") ||
            request.getRequestURI().equals("/api/members/logout")){
                filterChain.doFilter(request, response);
                return;
        } //로그인 또는 로그아웃은 통과

        // accessToken 검증과 refreshToken발급
        String accessToken = null; //TODO: 정의해야됨!
        if(!accessToken.isBlank()){
            //TODO: 정의해야됨!
        }
        filterChain.doFilter(request, response);
    }
    
}
