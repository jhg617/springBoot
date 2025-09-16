package com.sist.ex0916_memo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sist.ex0916_memo.vo.MemoVO;

@Mapper
public interface MemoMapper {
    List<MemoVO> all();
}
