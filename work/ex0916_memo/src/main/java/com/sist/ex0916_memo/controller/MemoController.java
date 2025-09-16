package com.sist.ex0916_memo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.ex0916_memo.service.MemoService;
import com.sist.ex0916_memo.vo.MemoVO;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/memo")
public class MemoController {
    @Autowired
    private MemoService mService;

    @RequestMapping("/all")
    public Map<String, Object> getAll() {
        Map<String, Object> map = new HashMap<>();
        List<MemoVO> list = mService.getAll();

        //준비된 map에 list를 저장하자
        map.put("m_list", list);
        map.put("length", list.size());

        return map;
    }
}
