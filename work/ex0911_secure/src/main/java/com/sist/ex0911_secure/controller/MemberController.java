package com.sist.ex0911_secure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sist.ex0911_secure.service.MemberService;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
public class MemberController {
    
    // DB활용을 위해 service객체 준비
    @Autowired
    private MemberService mService;

    // 로그인 처리를 위한 세션 준비
    @Autowired
    private HttpSession session;

    @RequestMapping
    public ModelAndView index() { //RestController이기 때문에 ModelAndView로 던져야함
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }
    
}
