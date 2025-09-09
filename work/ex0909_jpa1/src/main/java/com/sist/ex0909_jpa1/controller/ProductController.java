package com.sist.ex0909_jpa1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sist.ex0909_jpa1.repository.ProductRepository;
import com.sist.ex0909_jpa1.store.ProductJPO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/t1")
    public String test1() {
        ProductJPO product = new ProductJPO();
        //product.setPNum(100L);
        product.setPName("빈센트 아몬드나무");
        product.setPCompany("Art company");

        productRepository.save(product);
        return "test1";
    }

    @GetMapping("/t2")
    public String test2() {
        ProductJPO product = ProductJPO.builder() //builder를 사용하면 객체생성 없이 연속적으로 값을 저장한다.
                .pName("빈센트 해바라기")
                .pCompany("Art company")
                .build();
        productRepository.save(product); //Repository를 상속받아서 
        return "test2";
    }
    
    @GetMapping("/t3")
    public String test3() {
        
        List<ProductJPO> list = productRepository.findAll();

        StringBuffer sb = new StringBuffer();
        for(ProductJPO product : list) {
            sb.append(product.getPNum());
            sb.append(" / ");
            sb.append(product.getPName());
            sb.append(" / ");
            sb.append(product.getPCompany());
            sb.append(" / ");
            sb.append(product.getCvo1().getCName());
            sb.append(" / ");
            sb.append(product.getCvo1().getDesc());
            sb.append("<br/>");
        }
        return sb.toString();
    }
    
}
