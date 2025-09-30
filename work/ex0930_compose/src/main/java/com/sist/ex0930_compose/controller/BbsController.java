package com.sist.ex0930_compose.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.ex0930_compose.service.BbsService;
import com.sist.ex0930_compose.service.CommService;
import com.sist.ex0930_compose.util.Paging;
import com.sist.ex0930_compose.vo.BbsVO;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/board")
public class BbsController {
    @Autowired
    private BbsService bService;

    @Autowired
    private CommService cService;

    private int numPerPage = 7;
    private int pagePerBlock = 5;

    @RequestMapping("/list")
    public Map<String, Object> list(@RequestParam("bname") String bname,
            String searchType, String searchValue, String cPage) {
        int nowPage = 1;
        if(cPage != null)
            nowPage = Integer.parseInt(cPage);

        // bname이 무조건 인자로 받기로 되어있지만 공백일 때는
        // 기본값으로 "BBS"로 정하자
        if(bname.trim().length() == 0)
                bname = "BBS";

        //페이징 기법을 위해 우선 총 게시물 수를 알아내야 한다.
        int totalCount = bService.getTotalCount(bname, searchType, searchValue);

        //페이징 객체 생성
        Paging page = new Paging(numPerPage, pagePerBlock);
        page.setTotalCount(totalCount); //총 게시물 수를 지정
        page.setNowPage(nowPage); //현재 페이지 값 지정

        // 이제 페이징 객체로부터 begin과 end값을 얻어낸다.
        int begin = page.getBegin();
        int end = page.getEnd();

        // 화면에 표현할 게시물 목록
        List<BbsVO> list = bService.getList(
            bname, searchType, searchType, begin, end);

        //반환할 Map구조를 생성하자
        Map<String, Object> map = new HashMap<>();
        map.put("ar", list);
        map.put("bname", bname);
        map.put("nowPage", nowPage);
        map.put("totalCount", totalCount);
        map.put("totalPage", page.getTotalPage());
        map.put("length", list.size());

        return map;
    }

    @RequestMapping("/getBbs")
    public Map<String, Object> getBbs(@RequestParam String b_idx) {
        Map<String, Object> map = new HashMap<>();
        BbsVO vo = bService.getBbs(b_idx);
        map.put("vo", vo);

        return map;
    }
    
    @RequestMapping("/add")
    public Map<String, Object> addBbs(@RequestBody BbsVO vo) {
        Map<String, Object> map = new HashMap<>();

        //첨부파일 처리...스프링 예제 참조!

        int cnt = bService.addBbs(vo);
        map.put("cnt", cnt); //cnt가 0보다 크다면 정상적으로 저장완료!

        return map;
    }
    
}
