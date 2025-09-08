package com.sist.ex0908_bbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sist.ex0908_bbs.vo.CommVO;

@Mapper
public interface CommMapper {
    
    List<CommVO> list(String b_idx);
}
