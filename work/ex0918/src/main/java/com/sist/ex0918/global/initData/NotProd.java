package com.sist.ex0918.global.initData;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.sist.ex0918.domain.bbs.entity.service.BbsService;

@Configuration
@Profile({"dev", "test"}) //application.yml > profiles이 dev나 test가 아니면 움직이지 않는다.
public class NotProd {
    // 가짜데이터(개발때만 미리 데이터들 넣어주기 위함)

    @Bean //반드시 실행됨
    CommandLineRunner initData(BbsService bbsService){
        //인자로 BbsService를 받아서 실제 @Service가 붙어있으면 생성, 없으면 생성안함
        return args -> {
            bbsService.create("제목1", "마루치", "테스트입니다.");
            bbsService.create("제목2", "아라치", "테스트입니다.");
            bbsService.create("제목3", "이도", "테스트입니다.");
            bbsService.create("제목4", "을불", "테스트입니다.");
        };
    }
}
