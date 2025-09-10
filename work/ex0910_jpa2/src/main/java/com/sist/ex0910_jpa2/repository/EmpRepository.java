package com.sist.ex0910_jpa2.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sist.ex0910_jpa2.store.Emp;

@Repository
public interface EmpRepository extends JpaRepository<Emp, Long>{
    //JPQL

    //검색된 데이터가 없을때 null이 아닌 Optional.empty()를 반환한다.
    // 그러므로 값이 없을 때 보다 안전하게 처리된다.
    Optional<Emp> findByEmpno(Long empno);
    List<Emp> findByDeptno(String deptno); //부서번호검색
    
    List<Emp> findByJobAndDeptno(String job, String deptno); //job과 부서번호 검색

    // @Query 어노테이션은 Spring Data JPA에서 메서드에 직접 쿼리문을 작성한다는 의미
    // nativeQuery=true 는 JPQL이 아닌 순수 SQL문을 사용한다는 뜻 
    @Query(value = "select * from emp where job like concat('%',?1, '%') and deptno = ?2",
     nativeQuery = true) // 1은 첫번째 인자를 의미하고, 2는 두번째 인자를 의미한다.
    List<Emp> findByJobLikeAndDeptno(String job, String deptno); //Like로 job과 부서번호 검색

    List<Emp> findByJobContainingAndDeptno(String job, String deptno); //Containing로 job과 부서번호 검색
    List<Emp> findByEnameStartingWith(String ename); //StartingWith로 사원 이름 검색

    //문제:
    // 급여가 3000이하인 사원들의 정보를 입사일(hiredate)이 최근 순으로 출력
    // 하기 위한 repository의 함수를 정의하자!
    List<Emp> findBySalLessThanEqualOrderByHiredateDesc(BigDecimal sal);
}
