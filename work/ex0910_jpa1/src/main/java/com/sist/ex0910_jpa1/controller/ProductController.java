package com.sist.ex0910_jpa1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sist.ex0910_jpa1.repository.ProductRepository;
import com.sist.ex0910_jpa1.store.ProductJPO;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class ProductController {
    
    @Autowired
    private ProductRepository productRepository;

    //데이터 저장
    @GetMapping("/add")
    public String add() {
        ProductJPO p1 = ProductJPO.builder()
            .pNum(152L)
            .pName("비상")
            .pCompany("sist")
            .category1(3)
            .build();
        productRepository.save(p1);
        return "insert ok!";
    }
    
}
