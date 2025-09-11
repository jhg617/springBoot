package com.sist.ex0911_secure.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sist.ex0911_secure.vo.MemVO;

@Mapper
public interface MemberMapper {
    int reg(MemVO vo);

    MemVO login(String m_id);
}
