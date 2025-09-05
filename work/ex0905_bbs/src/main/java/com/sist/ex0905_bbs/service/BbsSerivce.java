package com.sist.ex0905_bbs.service;

import com.sist.ex0905_bbs.mapper.BbsMapper;
import com.sist.ex0905_bbs.vo.BbsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BbsSerivce {

    @Autowired
    private BbsMapper bbsMapper;

    // 목록 반환
    public BbsVO[] list(){
        BbsVO[] ar = null;
        List<BbsVO> list = bbsMapper.list(); //bbs.xml의 parameterType 맞춰야함
        if(list != null && list.size()>0){
            ar = new BbsVO[list.size()];
            list.toArray(ar);
        }
        return ar;
    }
}
