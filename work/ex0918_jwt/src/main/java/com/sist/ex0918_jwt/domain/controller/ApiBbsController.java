package com.sist.ex0918_jwt.domain.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.ex0918_jwt.domain.bbs.entity.Bbs;
import com.sist.ex0918_jwt.domain.bbs.entity.service.BbsService;
import com.sist.ex0918_jwt.global.result.ResultData;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bbs")
public class ApiBbsController {
    
    private final BbsService bbsService;

    @GetMapping("")
    public ResultData<List<Bbs>> getList() {

        // Map<String,Object> map = new HashMap<>();

        List<Bbs> list = bbsService.getList();

        // map.put("ar", list);
        // map.put("length", list.size());
        String msg = "fail";
        if (list != null && list.size() > 0)
            msg = "success";
        
        // return map;
        return ResultData.of(list.size(), msg, list);
    }
    
    @GetMapping("/{b_idx}")
    public ResultData<Bbs> getBbs(@PathVariable("b_idx") Long b_idx) {
        // Map<String,Object> map = new HashMap<>();

        Bbs bbs = bbsService.getBbs(b_idx);
        // map.put("bbs", bbs);

        String msg = "fail";
        int cnt = 0;
        if (bbs != null) {
            msg = "success";
            cnt = 1;
        }

        return ResultData.of(cnt, msg, bbs);
    }
    
    @PostMapping("/write")
    public ResultData<Bbs> write(@RequestBody Bbs bbs) {

        // 삽입연산을 하고 반환된 bbs는 java가 아니고 db단에서 auto increment된 값도 들어가 있도록 처리했다
        // -> entity의 @GeneratedValue(strategy = GenerationType.IDENTITY)를 통해서
        Bbs rtBbs = bbsService.create(bbs.getTitle(), bbs.getWriter(), bbs.getContent());

        String msg = "fail";
        int cnt = 0;
        if (rtBbs != null) {
            msg = "success";
            cnt = 1;
        }

        return ResultData.of(cnt, msg, rtBbs);
    }
    
}
