package com.sist.ex0911_jwt.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sist.ex0911_jwt.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class MemberController {
    @Autowired
    private MemberService mService;

    @GetMapping("/code")
    public Map<String, Object> genToken(@RequestParam(name = "mid") String mid) {
        Map<String, Object> result = new HashMap<>();

        String token = mService.makeToken(mid);
        result.put("access_token", token);

        return result;
    }
    
}
