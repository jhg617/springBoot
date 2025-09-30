package com.sist.ex0930_emp.mybatis.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor //기본생성자 생성
@AllArgsConstructor //전체를 받는 생성자

public class EmpVO {
    private String empno, ename, job, deptno;
}
