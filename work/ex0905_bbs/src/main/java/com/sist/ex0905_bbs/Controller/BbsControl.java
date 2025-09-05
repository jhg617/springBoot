package com.sist.ex0905_bbs.Controller;

import com.sist.ex0905_bbs.service.BbsSerivce;
import com.sist.ex0905_bbs.vo.BbsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class BbsControl {

    @Autowired
    private BbsSerivce bbsSerivce;

    @GetMapping("/list")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView("list"); //기본생성자 생성

        // 뷰페이지 표현할 데이터 가져오기
        BbsVO[] ar = bbsSerivce.list();
        mv.addObject("ar",ar);
        mv.setViewName("list");

        return mv;
    }
}
