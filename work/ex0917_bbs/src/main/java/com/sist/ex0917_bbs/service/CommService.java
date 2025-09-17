package com.sist.ex0917_bbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.ex0917_bbs.mapper.CommMapper;
import com.sist.ex0917_bbs.vo.CommVO;

@Service
public class CommService {
    @Autowired
    private CommMapper cMapper;

    public int addComm(CommVO vo){
        return cMapper.addComm(vo);
    }
}
