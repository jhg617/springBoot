package com.sist.ex0905_dept.controller;

import com.sist.ex0905_dept.service.DeptService;
import com.sist.ex0905_dept.vo.DeptVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController //String형으로 반환할때 주의해서 사용
public class DeptController {

    // 사용할 서비스 정의
    @Autowired
    private DeptService deptService;

    @GetMapping("/dept")
    public ModelAndView dept(){
        ModelAndView mv = new ModelAndView("dept");

        // 뷰 페이지에서 표현할 데이터 가져오기
        DeptVO[] ar = deptService.getTotal();
        mv.addObject("ar", ar);

        return mv;
    }
}
