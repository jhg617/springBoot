package com.sist.ex0910_jpa2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.ex0910_jpa2.repository.EmpRepository;
import com.sist.ex0910_jpa2.store.Emp;

@Service
public class EmpService {
    
    @Autowired
    private EmpRepository empRepository;

    //비지니스 로직
    public List<Emp> findAll() {
        return empRepository.findAll();
    }

    public Optional<Emp> findByEmpno(Long empno) {
        return empRepository.findByEmpno(empno);
    }

    public List<Emp> findByDeptno(String deptno) {
        return empRepository.findByDeptno(deptno);
    }
}
