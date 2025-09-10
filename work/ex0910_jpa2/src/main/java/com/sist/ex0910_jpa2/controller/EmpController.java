package com.sist.ex0910_jpa2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.ex0910_jpa2.service.EmpService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;




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
        // 명시적으로 @RequestParam에 파라미터 이름을 적어줘야 한다.
        return empService.findByEmpno(empno);
    }
    
    //직종과 부서검색을 and 조건으로 검색하고자 한다.
    // RequestMapping("/jobanddept")
    @RequestMapping("/jobanddept")
    public Object findByJobAndDeptno(@RequestParam String job, 
                                    @RequestParam String deptno) {
        return empService.findByJobAndDeptno(job, deptno);
    }

    @RequestMapping("/job_dept")
    public Object findByJobLikeAndDeptno(@RequestParam String job, 
                                    @RequestParam String deptno) {
        //return empService.findByJobLikeAndDeptno(job, deptno);
        return empService.findByJobContainingAndDeptno(job, deptno);
    }

    // getmapping으로 테스트 - *.request로 했을때는 됐음
    /* @GetMapping("/job_dept")
    public Object findByJobLikeAndDeptno(@RequestParam String job, 
                                    @RequestParam String deptno) {
        return empService.findByJobLikeAndDeptno(job, deptno);
        //return empService.findByJobContainingAndDeptno(job, deptno);
    } */

    @RequestMapping("/getEname")
    public Object findByEnameStartingWith(@RequestParam String ename) {
        return empService.findByEnameStartingWith(ename);
    }

}
