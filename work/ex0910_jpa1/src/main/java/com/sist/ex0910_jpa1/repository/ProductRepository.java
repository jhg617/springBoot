package com.sist.ex0910_jpa1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sist.ex0910_jpa1.store.ProductJPO;

@Repository //레파지토리 지정
public interface ProductRepository extends JpaRepository<ProductJPO, Long>{
    //ProductJPO는 product_t 테이블을 의미하며, 
    //Long은 엔티티의 기본 키(PK) 필드의 데이터 타입을 의미한다.(=pNum)
}
