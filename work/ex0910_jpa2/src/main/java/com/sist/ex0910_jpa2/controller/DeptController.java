package com.sist.ex0910_jpa2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.ex0910_jpa2.service.DeptService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/dept")
public class DeptController {
    
    @Autowired
    private DeptService deptService;

    @GetMapping("/all")
    public Object all() {
        return deptService.findAll();
    }
    
}
