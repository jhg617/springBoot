package com.sist.ex0918_jwt.domain.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sist.ex0918_jwt.domain.member.entity.Member;
import com.sist.ex0918_jwt.domain.member.repository.MemberRepository;
import com.sist.ex0918_jwt.global.jwt.JwtProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository mRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public Member join(String mid, String mname, String mpwd){
        Member mem = Member.builder()
            .mid(mid)
            .mname(mname)
            .mpwd(mpwd)
            .build();
        return mRepository.save(mem);
    }

    public Member authAndMakeToken(String mid, String mpwd){
        Member member = null;
        String accessToken = null;
        try {
            member = mRepository.findByMid(mid).orElseThrow(() ->
                        new RuntimeException("존재하지 않는 ID"));

            //위는 mid값만 가지고 검색한 Member이므로 다시 비밀번호가 맞는지?
            // 확인해야 한다.
            if(passwordEncoder.matches(mpwd, member.getMpwd())){
                //여기는 위의 RuntimeException이 발생하지 않을 때만 수행함!
                //  회원정보를 가지고 토큰 생성
                Map<String, Object> map = new HashMap<>();
                map.put("idx", member.getB_idx());
                map.put("mid", member.getMid());
                // map.put("mpwd", member.getMpwd()); //토큰에 넣는 것은 좋지 않아
                map.put("mname", member.getMname());
                map.put("write_date", member.getWrite_date());

                accessToken = jwtProvider.genToken(map, 60*60);
                String refreshToken = jwtProvider.genToken(map, 60*60*3);

                member.setAccessToken(accessToken);
                member.setRefreshToken(refreshToken);

                // DB에 UPDATE할거면 여기서 해도 된다.
                
            }//if문의 끝

        } catch (Exception e) {}
        System.out.println("ACCESSTOKEN:"+accessToken);
        return member;

    }
}
