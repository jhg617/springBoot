package com.sist.ex0911_jwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sist.ex0911_jwt.vo.MemVO;

@Repository
public interface MemberRepository extends JpaRepository<MemVO, String> {
    
    Optional<MemVO> findBymId(String mid);
}
