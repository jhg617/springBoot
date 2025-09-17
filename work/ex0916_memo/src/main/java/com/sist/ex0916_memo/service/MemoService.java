package com.sist.ex0916_memo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.ex0916_memo.mapper.MemoMapper;
import com.sist.ex0916_memo.vo.MemoVO;

@Service
public class MemoService {
    
    @Autowired
    private MemoMapper mapper;

    public List<MemoVO> getAll(){
        return mapper.all();
    }

    public MemoVO getMemo(String idx){
        return mapper.getMemo(idx);
    }

}
