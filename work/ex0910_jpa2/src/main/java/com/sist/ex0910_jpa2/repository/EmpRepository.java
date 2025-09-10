package com.sist.ex0910_jpa2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sist.ex0910_jpa2.store.Emp;

@Repository
public interface EmpRepository extends JpaRepository<Emp, Long>{
    //JPQL

    //검색된 데이터가 없을때 null이 아닌 Optional.empty()를 반환한다.
    // 그러므로 값이 없을 때 보다 안전하게 처리된다.
    Optional<Emp> findByEmpno(Long empno);
    List<Emp> findByDeptno(String deptno); //부서번호검색
}
