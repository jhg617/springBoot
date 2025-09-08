package com.sist.ex0908_bbs.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Request;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sist.ex0908_bbs.service.BbsService;
import com.sist.ex0908_bbs.service.CommService;
import com.sist.ex0908_bbs.util.FileRenameUtil;
import com.sist.ex0908_bbs.util.Paging;
import com.sist.ex0908_bbs.vo.BbsVO;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
public class BbsController {

    private final CommService commService;
    
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private BbsService bbsService;

    @Autowired
    private ServletContext application;
    
    @Value("${server.editor.path}") //application.properties에 설정한 값
    private String editor_img;

    @Value("${server.upload.path}") //application.properties에 설정한 값
    private String bbs_upload;

    private int numPerPage = 7; // 한 페이지당 보여질 게시물 수
    private int pagePerBlock = 5;

    BbsController(CommService commService) {
        this.commService = commService;
    } // 한 블럭당 표현할 페이지 수

    @GetMapping("/list")
    public ModelAndView getBbsList(@RequestParam String bname, String cPage) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(bname+"/list");

        int nowPage = 1; //cPage가 안들어오면 무조건 1로 지정

        if(cPage != null && !cPage.equals("")) {
            nowPage = Integer.parseInt(cPage);
        }

        if(bname == null || bname.equals("")) {
            bname = "BBS";
        }

        int totalCount = bbsService.getTotalCount(bname); // 총 게시물의 수

        // 페이징객체 생성
        Paging page = new Paging(nowPage, totalCount, numPerPage, pagePerBlock, bname);

        BbsVO[] ar = bbsService.getList(bname, page.getBegin(), page.getEnd());

        mv.addObject("totalCount", totalCount);
        mv.addObject("bname", bname);
        mv.addObject("ar", ar);
        mv.addObject("page", page);
        mv.addObject("pageResult", page.getPagingHTML());

        return mv;
    }

    @GetMapping("/write")
    public ModelAndView getWriteForm(@RequestParam String bname, String cPage) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(bname+"/write");

        mv.addObject("bname", bname);
        mv.addObject("cPage", cPage);
        return mv;
    }

    @PostMapping("saveImg")
    public Map<String, Object> saveImg(MultipartFile upload) {
        //반환형 준비
        Map<String, Object> entity = new HashMap<>();

        //첨부파일이 전달되어 왔다면
        if (upload.getSize() > 0){
            //파일을 저장할 위치(editor_img)를 절대경로화 시킨다.
            String realPath = application.getRealPath(editor_img);

            //파일명을 얻어낸다.
            String oname = upload.getOriginalFilename();

            // 동일한 파일명이 있을 때만 파일명을 변경하자!
            String fname = FileRenameUtil.checkSameFileName(oname, realPath);
            try {
                //업로드된 파일을 저장한다.
                upload.transferTo(new File(realPath, fname)); //파일 저장(업로드)
            } catch (Exception e) {
                // TODO: handle exception
            }
            // 업로드된 파일의 전체경로(파일명이 포함된 경로)를
            // map에 담자
            String url_path = request.getContextPath();// ex) sist.co.kr/

            //JSON형식으로 반환하기 위해 map에 저장
            entity.put("img_url", url_path + editor_img + fname);
        }
        
        return entity;
    }
    
    @RequestMapping(value = "write", method=RequestMethod.POST)
    public ModelAndView write(BbsVO vo) {
        ModelAndView mv = new ModelAndView();
        
        String c_type = request.getContentType();
        if(c_type.startsWith("multipart")){
            //파일이 첨부되는 폼에서 호출된 경우
            MultipartFile f = vo.getFile(); //파일이름 뽑기
            String fname = null;
            if(f != null && f.getSize() > 0) {
                //첨부된 파일이 있을 경우 - 파일이 저장될 위치를 절대경로화 시킨다.
                String realPath = application.getRealPath(bbs_upload);
                fname = f.getOriginalFilename();
                // 동일한 파일명이 있을 때만 파일명을 변경하자!
                fname = FileRenameUtil.checkSameFileName(fname, realPath);

                try {
                    // 파일 업로드(upload폴더에 저장)
                    f.transferTo(new File(realPath, fname));
                    vo.setFile_name(fname);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } //if의 끝
            vo.setIp(request.getRemoteAddr()); //IP저장
            bbsService.add(vo); //vo를 DAO로 전달하여 DB에 저장하도록 한다.
            
        }
        // list화면으로 이동하기 위해 다시 list라는 RequestMapping을
            // redirect로 호출한다
            mv.setViewName("redirect:/list?bname="+vo.getBname());
            return mv;
    }
    
}
