package com.sist.ex0918_jwt.domain.member.repository;

import com.sist.ex0918_jwt.domain.member.entity.Member;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>{
    Optional<Member> findByMid(String mid);
}
