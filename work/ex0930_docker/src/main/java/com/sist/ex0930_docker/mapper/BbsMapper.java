package com.sist.ex0930_docker.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sist.ex0930_docker.vo.BbsVO;

@Mapper
public interface BbsMapper {
    List<BbsVO> list(String bname, String searchType, String searchValue,
        int begin, int end);

    int totalCount(Map<String, Object> map);

    BbsVO get_bbs(String b_idx);
    int add(BbsVO vo);
    int hit(String b_idx);
    int edit(Map<String, Object> map);
    int del(String b_idx);
    //bbs.xml에 정의가 되어있어야 하며, 여기서는 명시하지 않아도 괜찮다.
}
