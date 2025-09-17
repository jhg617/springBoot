package com.sist.ex0917_bbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sist.ex0917_bbs.vo.CommVO;

@Mapper
public interface CommMapper {
    List<CommVO> commList(String b_idx);
    
    int addComm(CommVO vo);
}
