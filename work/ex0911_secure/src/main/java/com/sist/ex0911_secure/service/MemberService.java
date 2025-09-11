package com.sist.ex0911_secure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sist.ex0911_secure.mapper.MemberMapper;
import com.sist.ex0911_secure.vo.MemVO;

@Service
public class MemberService {
    
    @Autowired
    private MemberMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder; //비밀번호 암호화 목적

    //회원가입
    public int regMember(MemVO vo){
        //reg.jsp에서 전달되는 m_id, m_pw, m_name등이 컨트롤러에서
        // vo로 받은 것을 그대로 현재 메서드의 vo라는 인자로 들어온다.
        // 이중 비밀번호를 암호화 하자!
        String pw = passwordEncoder.encode(vo.getM_pw()); //암호화된 비밀번호임
        vo.setM_pw(pw); //vo에 암호화된 비밀번호 저장

        //int cnt = mapper.reg(vo);
        //return cnt;
        return mapper.reg(vo); //DB에 저장
    }

    //로그인
    public MemVO login(MemVO vo){
        // DB로부터 vo에 있는 m_id를 보내어
        // 해당 MemVO를 받아온다.
        MemVO mvo = mapper.login(vo.getM_id());

        if(mvo != null){
            // 이때 비밀번호가 일치하는지는
            // passwordEncode에게 물어봐야 한다.
            if(passwordEncoder.matches(
                vo.getM_pw(), mvo.getM_pw())); //vo가 가진 패스워드, db가 가진 패스워드
                return mvo;
        }
        return null; //패스워드나 id가 다르면 null을 반환
    }

}
