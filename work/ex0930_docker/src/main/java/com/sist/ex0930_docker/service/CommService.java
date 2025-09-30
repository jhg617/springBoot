package com.sist.ex0930_docker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.ex0930_docker.mapper.CommMapper;
import com.sist.ex0930_docker.vo.CommVO;

@Service
public class CommService {
    @Autowired
    private CommMapper cMapper;

    public int addComm(CommVO vo){
        return cMapper.addComm(vo);
    }
}
