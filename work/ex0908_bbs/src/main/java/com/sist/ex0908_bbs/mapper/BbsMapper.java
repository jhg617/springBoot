package com.sist.ex0908_bbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sist.ex0908_bbs.vo.BbsVO;

@Mapper
public interface BbsMapper {
    
    int totalCount(String bname);

    List<BbsVO> list(String bname, int begin, int end);

    int add(BbsVO vo);

    BbsVO getBbs(String b_idx);
}
