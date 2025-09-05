package com.sist.ex0905_emp;

import com.sist.ex0905_emp.mybatis.service.EmpService;
import com.sist.ex0905_emp.mybatis.vo.EmpVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class EmpControl {

    @Autowired
    private EmpService empService;

    @GetMapping("/emp")//Get으로 호출할때
    public ModelAndView empList(){
        ModelAndView mv = new ModelAndView();

        // 뷰페이지에서 표현할 데이터 받기
        EmpVO[] ar = empService.getAll();
        mv.addObject("ar", ar);
        mv.setViewName("emp"); //emp.jsp
        return mv;
    }
}
