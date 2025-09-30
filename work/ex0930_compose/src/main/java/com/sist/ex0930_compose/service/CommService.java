package com.sist.ex0930_compose.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.ex0930_compose.mapper.CommMapper;
import com.sist.ex0930_compose.vo.CommVO;

@Service
public class CommService {
    @Autowired
    private CommMapper cMapper;

    public int addComm(CommVO vo){
        return cMapper.addComm(vo);
    }
}
