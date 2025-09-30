package com.sist.ex0930_emp;

import com.sist.ex0930_emp.mybatis.service.EmpService;
import com.sist.ex0930_emp.mybatis.vo.EmpVO;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class EmpControl {
    @Autowired
    private EmpService empService;

    @GetMapping("/emp")//Get으로 호출할때
    public Map<String, Object> empList(){

        Map<String, Object> map = new HashMap<>();
        // 뷰페이지에서 표현할 데이터 받기
        EmpVO[] ar = empService.getAll();
        map.put("ar", ar);
        //map.put("emp"); //emp.jsp
        return map;
    }
}
