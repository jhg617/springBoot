package com.sist.ex0910_jpa2.service;

import java.math.BigDecimal;
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

    public List<Emp> findByJobAndDeptno(String job, String deptno) {
        return empRepository.findByJobAndDeptno(job, deptno);
    }

    //Like 사용
    public List<Emp> findByJobLikeAndDeptno(String job, String deptno) {
        return empRepository.findByJobLikeAndDeptno(job, deptno);
    }

    //Containing 사용
    public List<Emp> findByJobContainingAndDeptno(String job, String deptno) {
        return empRepository.findByJobContainingAndDeptno(job, deptno);
    }

    public List<Emp> findByEnameStartingWith(String ename){
        return empRepository.findByEnameStartingWith(ename);
    }

    public List<Emp> findBySalLessThanEqualOrderByHiredateDesc(BigDecimal sal){
        return empRepository.findBySalLessThanEqualOrderByHiredateDesc(sal);
    }
}
