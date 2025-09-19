package com.sist.ex0918.domain.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.ex0918.domain.bbs.entity.Bbs;
import com.sist.ex0918.domain.bbs.entity.service.BbsService;
import com.sist.ex0918.global.result.ResultData;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bbs")
public class ApiBbsController {

    private final BbsService bbsService;

    @GetMapping("")
    public ResultData<List<Bbs>> getList() {
        //Map<String, Object> map = new HashMap<>();
        List<Bbs> list = this.bbsService.getList();
        //map.put("ar", list);
        //map.put("length", list.size());
        String msg = "fail";
        if(list != null && list.size() > 0)
            msg = "success";

        //return map;
        return ResultData.of(list.size(), msg, list);
    }

    /* @GetMapping("/{b_idx}")
    public Map<String, Object> getBbs(@PathVariable("b_idx") Long b_idx) {
        Map<String, Object> map = new HashMap<>();
        Bbs bbs = bbsService.getBbs(b_idx);
        map.put("bbs", bbs);

        return map;
    } */

    @GetMapping("/{b_idx}")
    public ResultData<Bbs> getBbs(@PathVariable("b_idx") Long b_idx) {
        Bbs bbs = this.bbsService.getBbs(b_idx);
        String msg = "fail";
        int cnt = 0;
        if(bbs != null){
            msg = "success";
            cnt = 1;
        }
        //return map;
        return ResultData.of(cnt, msg, bbs);
    }
    
    @PostMapping("/write")
    public ResultData<Bbs> write(@RequestBody Bbs bbs) {
        Bbs bvo = bbsService.create(
            bbs.getTitle(), bbs.getWriter(), bbs.getContent());

            // 저장된 결과가 bvo이고 그 안에 b_idx라는 기본키값도 가지고 있다.
            // b_idx값을 다른 테이블에 저장도 가능하다.

        String msg = "fail";
        int cnt = 0;
        if(bvo != null){
            msg = "success";
            cnt = 1;
        }
        //return map;
        return ResultData.of(cnt, msg, bvo);
        
    }
    
}
