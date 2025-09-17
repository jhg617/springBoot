package com.sist.ex0917_bbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.ex0917_bbs.mapper.BbsMapper;
import com.sist.ex0917_bbs.vo.BbsVO;

@Service
public class BbsService {
    @Autowired
    private BbsMapper bMapper;

    public int getTotalCount(String bname, String searchType,
            String searchValue){
        Map<String, Object> map = new HashMap<>();
        if(bname != null)
            map.put("bname", bname);
        if(searchType != null)
            map.put("searchValue", searchValue);
        if(searchValue != null)
            map.put("searchValue", searchValue);
        return bMapper.totalCount(map);
    }

    public List<BbsVO> getList(String bname, String searchType,
            String searchValue, int begin, int end){
        return bMapper.list(bname, searchType, searchValue, begin, end);
    }

    public BbsVO getBbs(String b_idx){
        return bMapper.get_bbs(b_idx);
    }

    public int addBbs(BbsVO vo){
        return bMapper.add(vo);
    }
}
