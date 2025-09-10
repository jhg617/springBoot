package com.sist.ex0910_jpa1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sist.ex0910_jpa1.repository.CategoryRepository;
import com.sist.ex0910_jpa1.repository.ProductRepository;
import com.sist.ex0910_jpa1.store.Category1JPO;
import com.sist.ex0910_jpa1.store.ProductJPO;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class ProductController {
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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
    
    @GetMapping("/cList")
    public String getClist () {
        List<Category1JPO> list = categoryRepository.findAll();

        StringBuffer sb = new StringBuffer();

        for(Category1JPO cvo : list) {
            sb.append(cvo.getIdx());
            sb.append(" / ");
            sb.append(cvo.getCName());
            sb.append("<br/>");
            List<ProductJPO> pList = cvo.getPList();
            for(ProductJPO pvo : pList) {
                sb.append("&nbsp;&nbsp;&nbsp;");
                sb.append(pvo.getPNum());
                sb.append(". ");
                sb.append(pvo.getPName());
                sb.append("<br/>");
            }
        }
        return sb.toString();
    }
    
}
