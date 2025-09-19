package com.sist.ex0918_jwt.domain.bbs.entity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sist.ex0918_jwt.domain.bbs.entity.Bbs;
import com.sist.ex0918_jwt.domain.bbs.entity.repository.BbsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 초기화되지 않은 final필드나 NonNull이 붙은 필드들에 대해 생성해줌
public class BbsService {
    private final BbsRepository bbsRepository;

    public List<Bbs> getList(){
        return bbsRepository.findAll();
    }

    public Bbs getBbs(Long b_idx){
        Bbs bbs = null;

        Optional<Bbs> opt = bbsRepository.findById(b_idx);

        if (!opt.isEmpty()) {
            bbs = opt.get();
        }

        return bbs;
    }

    public Bbs save(String title, String writer, String content){
        Bbs bbs = Bbs.builder()
            .title(title)
            .writer(writer)
            .content(content)
            .build();
        return this.bbsRepository.save(bbs);
    }

    public Bbs create(String title, String writer, String content){
        Bbs bbs = Bbs.builder().title(title).writer(writer).content(content).build();
        return this.bbsRepository.save(bbs);
    }
}
