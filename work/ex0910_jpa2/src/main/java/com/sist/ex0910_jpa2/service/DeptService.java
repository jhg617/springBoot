package com.sist.ex0910_jpa2.service;

import org.springframework.stereotype.Service;

import com.sist.ex0910_jpa2.repository.DeptRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //@Autowired 사용안해도됨. 왜?
// 파라미터로 받는 생성자(Constructor)를 자동으로 생성해주는 롬복(Lombok) 어노테이션이다.
public class DeptService {

    private final DeptRepository deptRepository; //Dept생성자 생성해줌
    public Object findAll(){
        return deptRepository.findAll();
    }

    public Object findByDeptno(Long deptno) {
        return deptRepository.findByDeptno(deptno);
    }
    
}
