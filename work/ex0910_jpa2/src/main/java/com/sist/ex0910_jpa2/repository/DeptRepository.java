package com.sist.ex0910_jpa2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sist.ex0910_jpa2.store.Dept;

@Repository
public interface DeptRepository extends JpaRepository<Dept, Long>{
    Optional<Dept> findByDeptno(Long deptno);
}
