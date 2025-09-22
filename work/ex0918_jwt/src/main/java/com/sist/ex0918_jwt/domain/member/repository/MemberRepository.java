package com.sist.ex0918_jwt.domain.member.repository;

import com.sist.ex0918_jwt.domain.member.entity.Member;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MemberRepository extends JpaRepository<Member, Long>{
    Optional<Member> findByMid(String mid);

    Optional<Member> findByRefreshToken(String refreshToken);

    @Modifying
    @Transactional
    @Query("UPDATE Member m SET m.refreshToken = :refreshToken WHERE m.b_idx = :b_idx")
    void updateRefreshToken(@Param("b_idx") Long b_idx,
        @Param("refreshToken") String refreshToken);
}
