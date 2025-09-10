package com.sist.ex0910_jpa2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.ex0910_jpa2.service.EmpService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestParam;



@RestController // Responsbody + Controller => json으로 반환해줌
@RequiredArgsConstructor //autowired 사용안해도 EmpService를 잡아서 생성자를 만들어줌.
@RequestMapping("/emp") //클래스에 경로 지정하면 무조건 해당 경로를 통해 처리된다.
public class EmpController {
    private final EmpService empService;

    @RequestMapping("/all")
    public Object findAll() {
        return empService.findAll();
    }

    @RequestMapping("/getEmpno")
    public Object findbyEmpno(@RequestParam("empno") Long empno) {
        return empService.findByEmpno(empno);
    }
    
}
