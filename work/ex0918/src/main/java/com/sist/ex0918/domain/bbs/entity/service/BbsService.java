package com.sist.ex0918.domain.bbs.entity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sist.ex0918.domain.bbs.entity.Bbs;
import com.sist.ex0918.domain.bbs.entity.repository.BbsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //Autowired 안해도됨,
                        //초기화 되지 않은 final필드나, @NonNull이 붙은 필드들에 대해 처리한다.
public class BbsService {
    private final BbsRepository bbsRepository;

    public List<Bbs> getList(){
        return bbsRepository.findAll();
    }

    public Bbs getBbs(Long b_idx){
        Bbs bbs = null;
        Optional<Bbs> opt = bbsRepository.findById(b_idx);
        if(!opt.isEmpty())
            bbs = opt.get();

        return bbs;
    }

    public Bbs create(String title, String writer, String content){
        Bbs bbs = Bbs.builder() //생성하는 시점에서 생성할 객체를 명시하여 오류 방지
            .title(title)
            .writer(writer)
            .content(content)
            .build();

        return this.bbsRepository.save(bbs); //DB에 저장
    }
    
}
