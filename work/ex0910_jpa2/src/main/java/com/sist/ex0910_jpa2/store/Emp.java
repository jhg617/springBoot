package com.sist.ex0910_jpa2.store;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "emp")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Emp {
    @Id
    @GeneratedValue
    private Long empno;
    private String ename, job, sal;
    private LocalDate hiredate;

    @Column(name = "deptno", insertable = false, updatable = false) //외래키 지정(삽입/수정 불가)
    private String deptno;
}
