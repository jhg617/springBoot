package com.sist.ex0909_jpa1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sist.ex0909_jpa1.store.ProductJPO;

@Repository
public interface ProductRepository extends JpaRepository<ProductJPO, Long>  {
    
}