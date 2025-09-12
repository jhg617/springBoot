package com.sist.ex0911_jwt.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sist.ex0911_jwt.jwt.JwtProvider;
import com.sist.ex0911_jwt.repository.MemberRepository;
import com.sist.ex0911_jwt.vo.MemVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    //제한자가 final이면 상수이기때문에 정의할때 반드시 값을 넣어줘야 한다.
    //멤버변수를 전부 받는 생성자를 @RequiredArgsConstructor 으로써 생성한다.
    private final MemberRepository mRepository;
    private final JwtProvider jwtProvider;

    public String makeToken(String mid){
        MemVO mvo = null;
        String token = null;
        Optional<MemVO> vo = mRepository.findBymId(mid);
        if(vo.isPresent()){
            mvo = vo.get();
        
            //mvo를 Map구조로 변환!
            Map<String, Object> map = new HashMap<>();
            map.put("mIdx", mvo.getMIdx());
            map.put("mId", mvo.getMId());
            map.put("mName", mvo.getMName());

            token = jwtProvider.genToken(map, 60);
        }
        return token;
    }

}
