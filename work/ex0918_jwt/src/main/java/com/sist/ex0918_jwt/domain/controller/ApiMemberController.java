package com.sist.ex0918_jwt.domain.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.ex0918_jwt.domain.member.entity.Member;
import com.sist.ex0918_jwt.domain.service.MemberService;
import com.sist.ex0918_jwt.global.result.ResultData;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class ApiMemberController {
    
    private final MemberService mService;
    private final HttpServletResponse response;

    @PostMapping("/login")
    public ResultData<Member> login(@RequestBody Member member) {
    //public ResultData<Member> login(String mid, String mpwd){
        int cnt = 0;
        String msg = "fail";

        // JWT를 생성
        //Member mem = mService.authAndMakeToken(mid, mpwd);
        Member mem = mService.authAndMakeToken(member.getMid(),
            member.getMpwd());

        if(mem != null){
            ResponseCookie cookie = ResponseCookie.from(
                "accessToken", mem.getAccessToken())
                .path("/") //특정도메인에서 사용하도록 함
                .sameSite("None") // CSRF 관련 문제를 해결
                .httpOnly(true) // 클라이언트 등을 통해 쿠키가 탈취되는것을 방지
                .secure(true) //쿠키가 탈취 당해도 암호화가 되어 있으서 안전함
                .build();
            response.addHeader("Set-Cookie", cookie.toString());

            cnt = 1;
            msg = "success";
        }
        return ResultData.of(cnt, msg, mem);
    }
    
}
