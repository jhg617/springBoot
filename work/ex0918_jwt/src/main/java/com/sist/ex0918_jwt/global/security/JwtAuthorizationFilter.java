package com.sist.ex0918_jwt.global.security;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sist.ex0918_jwt.domain.service.MemberService;
import com.sist.ex0918_jwt.global.result.ResultData;
import com.sist.ex0918_jwt.global.service.RequestService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter{

    private final MemberService memberService;
    private final RequestService requestService;

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
        //String accessToken = null;

        // accessToken 검증 또는 refreshToken 발급
        String accessToken = requestService.getCookie("accessToken");

        if(!accessToken.isBlank()){
            //accessToken이 만료되었는지 알아내고, 만료되었다면 refreshToken을
            // 얻어내어 검증한 후 accessToken 받아낸다.
            if(!memberService.validateToken(accessToken)){
                //accessToken이 만료된 경우
                String refreshToken = requestService.getCookie("refeshToken");

                ResultData<String> resultData = 
                    memberService.refreshAccessToken(refreshToken); //refresh토큰을 얻어낸다.

                requestService.setHeaderCookie("accessToken",
                    resultData.getData());

                accessToken = resultData.getData();
            }//if문의 끝
                JwtUser jwtUser = memberService.getUserFromAccessToken(
                    accessToken);

                //위에서 받은 jwtUser라는 객체를 인가처리
                requestService.setMember(jwtUser);
        }
        filterChain.doFilter(request, response);

    }
    
}
