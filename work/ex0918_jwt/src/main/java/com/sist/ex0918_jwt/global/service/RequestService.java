package com.sist.ex0918_jwt.global.service;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.http.ResponseCookie;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.sist.ex0918_jwt.domain.member.entity.Member;
import com.sist.ex0918_jwt.domain.service.MemberService;
import com.sist.ex0918_jwt.global.security.JwtUser;

import jakarta.persistence.EntityManager;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequestScope
@RequiredArgsConstructor
public class RequestService {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final MemberService memberService;
    private final EntityManager entityManager;
    private Member member;

    //JweAuthorizationFilter에 있는 getCookie가져오자
    public String getCookie(String name){
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return "";
        }

        return Arrays.stream(cookies) //cookies배열에서 스트림생성
            .filter(cookie -> cookie.getName().equals(name))//name과 같은 이름을 가진 쿠키만 필터링한다.
            .findFirst() //필터링된 결과가 여러 개가 있을 수 있는데 첫번째것만
            .map(Cookie::getValue) //찾은 쿠키 값 가져오기 Cookie를 반환하는
            .orElse(""); //getValue함수를 참조하고 필터링된 쿠키가 없다면
    }                      //공백 반환

    //JweAuthorizationFilter에 있는 setHeaderCookie가져오자
    public void setHeaderCookie(String tokenName, String token){
        ResponseCookie cookie = ResponseCookie.from(tokenName, token)
                .path("/")
                .sameSite("None")
                .secure(true)
                .httpOnly(true)
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
    }

    public Member getMember(){
        if(checkLogin())
            return null;

        if(member == null)
            member = entityManager.getReference(Member.class,
getJwtUser().getMid());
        return member;
    }

    public void setMember(JwtUser jwtUser){
        //JweAuthorizationFilter에 있는 인가처리 부분을 가져오자

SecurityContextHolder.getContext().setAuthentication(jwtUser.getAuthentication());
    }

    public JwtUser getJwtUser(){
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(context -> context.getAuthentication())
                .filter(authentication -> authentication.getPrincipal()
instanceof JwtUser)
                .map(authentication ->
(JwtUser)authentication.getPrincipal())
                .orElse(null);
    }

    private boolean checkLogin(){
        return getJwtUser() != null;
    }

    private boolean isLougout(){
        return !checkLogin();
    }

    //로그아웃시 호출! 전달되는 토큰명으로 null로 지정하고 maxAge를 0으로
     // 지정한 것을 다시 쿠키에 지정하면 삭제하는 것이다.
    public void removeHeaderCookie(String tokenName){
        ResponseCookie cookie = ResponseCookie.from(tokenName, null)
                .path("/")
                .sameSite("None")
                .secure(true)
                .httpOnly(true)
                .maxAge(0)
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
        }
}
