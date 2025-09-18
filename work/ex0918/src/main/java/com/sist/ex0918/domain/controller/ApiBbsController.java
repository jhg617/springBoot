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


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bbs")
public class ApiBbsController {

    private final BbsService bbsService;

    @GetMapping("")
    public ResultData<List<Bbs>> getList() {
        //Map<String, Object> map = new HashMap<>();
        List<Bbs> list = bbsService.getList();
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
    
}
