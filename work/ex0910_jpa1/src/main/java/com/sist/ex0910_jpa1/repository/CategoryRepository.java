package com.sist.ex0910_jpa1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sist.ex0910_jpa1.store.Category1JPO;

@Repository
public interface CategoryRepository extends JpaRepository<Category1JPO, Integer>{
   //제네릭타입은 무조건 객체자료형을 사용해야됨(int x, Integer o)
   
}
