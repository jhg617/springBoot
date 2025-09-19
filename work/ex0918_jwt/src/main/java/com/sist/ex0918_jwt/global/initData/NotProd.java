package com.sist.ex0918_jwt.global.initData;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sist.ex0918_jwt.domain.bbs.entity.service.BbsService;
import com.sist.ex0918_jwt.domain.member.entity.Member;
import com.sist.ex0918_jwt.domain.service.MemberService;

@Configuration //무조건 수행
@Profile({"dev","test"}) //application.yml 의 profiles:active:dev 일 경우와 test는 서버가 꺼지고 재실행해도 데이터 삭제 안됨
public class NotProd {
    //가짜 데이터(개발때만 미리 데이터들을 넣어주기 위함)
    @Bean
    CommandLineRunner initData(BbsService bbsService,
    MemberService memberService, PasswordEncoder passwordEncoder){ //@Service 를 찾아서 가져옴
        String pwd = passwordEncoder.encode("1111");

        return args -> {
            Member mem3 = memberService.join("maru", "마루치", pwd);
            Member mem4 = memberService.join("admin", "어두일미", pwd);

            bbsService.create("제목은 test1", "이름은 test1", "내용은 test1");
            bbsService.create("제목은 test2", "이름은 test2", "내용은 test2");
            bbsService.create("제목은 test3", "이름은 test3", "내용은 test3");
        };
    }
}

