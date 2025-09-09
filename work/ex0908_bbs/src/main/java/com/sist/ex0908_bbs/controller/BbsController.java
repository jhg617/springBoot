package com.sist.ex0908_bbs.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Request;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.sist.ex0908_bbs.config.DbConfig;
import com.sist.ex0908_bbs.service.BbsService;
import com.sist.ex0908_bbs.service.CommService;
import com.sist.ex0908_bbs.util.FileRenameUtil;
import com.sist.ex0908_bbs.util.Paging;
import com.sist.ex0908_bbs.vo.BbsVO;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
public class BbsController {

    private final DbConfig dbConfig;

    private final CommService commService;
    
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private BbsService bbsService;

    @Autowired
    private ServletContext application;

    @Autowired
    private HttpSession session;
    
    @Value("${server.editor.path}") //application.properties에 설정한 값
    private String editor_img;

    @Value("${server.upload.path}") //application.properties에 설정한 값
    private String bbs_upload;

    private int numPerPage = 7; // 한 페이지당 보여질 게시물 수
    private int pagePerBlock = 5;

    BbsController(CommService commService, DbConfig dbConfig) {
        this.commService = commService;
        this.dbConfig = dbConfig;
    } // 한 블럭당 표현할 페이지 수

    @RequestMapping("/list")
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
    
    // @RequestMapping(value = "write", method=RequestMethod.POST)
    // public ModelAndView write(BbsVO vo) {
    //     ModelAndView mv = new ModelAndView();
        
    //     String c_type = request.getContentType();
    //     if(c_type.startsWith("multipart")){
    //         //파일이 첨부되는 폼에서 호출된 경우
    //         MultipartFile f = vo.getFile(); //파일이름 뽑기
    //         String fname = null;
    //         if(f != null && f.getSize() > 0) {
    //             //첨부된 파일이 있을 경우 - 파일이 저장될 위치를 절대경로화 시킨다.
    //             String realPath = application.getRealPath(bbs_upload);
    //             fname = f.getOriginalFilename();
    //             // 동일한 파일명이 있을 때만 파일명을 변경하자!
    //             fname = FileRenameUtil.checkSameFileName(fname, realPath);

    //             try {
    //                 // 파일 업로드(upload폴더에 저장)
    //                 f.transferTo(new File(realPath, fname));
    //                 vo.setFile_name(fname);
    //             } catch (Exception e) {
    //                 e.printStackTrace();
    //             }
    //         } //if의 끝
    //         vo.setIp(request.getRemoteAddr()); //IP저장
    //         bbsService.add(vo); //vo를 DAO로 전달하여 DB에 저장하도록 한다.
            
    //     }
    //     // list화면으로 이동하기 위해 다시 list라는 RequestMapping을
    //         // redirect로 호출한다
    //         mv.setViewName("redirect:/list?bname="+vo.getBname());
    //         return mv;
    // }

    @PostMapping("/write")
    public ModelAndView write2(BbsVO vo) {
        ModelAndView mv = new ModelAndView();
        
        // 전달되어오는 파라미터들을 모두 vo에 들어있다.
        // 중요한것은 첨부파일이 있었는지? 없었는지?....
        MultipartFile file = vo.getFile();
        if(file.getSize() > 0) {
            // 파일이 첨부된 상태일때만 업로드한다.

            //업로드할 위치(절대경로)
            String realPath = application.getRealPath(bbs_upload);

            //원본 파일명
            String oname = file.getOriginalFilename();

            //같은 파일명이 있다면 파일명 변경
            String fname = FileRenameUtil.checkSameFileName(oname, realPath);

            try {
                file.transferTo(new File(realPath, fname)); //서버에 업로드(저장)
                vo.setFile_name(fname);
                vo.setOri_name(oname);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        vo.setIp(request.getRemoteAddr()); //vo에 작성자 IP 담기
        int result = bbsService.add(vo); //DB에 저장

        // 목록으로 돌아간다.
        mv.setViewName("redirect:/list?bname="+vo.getBname());
        return mv;
    }

    //게시글 보기기능
    // 조회수 증가처리, 읽은 게시물은 조회수 증가를 하지 않는다.
    // 조회수 증가처리는 세션을 활용하여 처리한다.
    @GetMapping("/view")
    public ModelAndView getBbs(@RequestParam String bname,
        @RequestParam String b_idx, String cPage) { //bname, b_idx는 필수로 파라미터로 받아야함
        ModelAndView mv = new ModelAndView();
        
        //세션에 read_list라는 이름으로 저장된 객체를 얻어내자!
        Object obj = session.getAttribute("read_list");
        ArrayList<BbsVO> list = null;

        //obj가 null이 아니면 obj를 형변환 시켜서 list에 담자
        if(obj != null) {
            list = (ArrayList<BbsVO>)obj;
        } else {
            list = new ArrayList<BbsVO>();
            session.setAttribute("read_list", list); //세션에 저장
        }

        //사용자가 선택한 게시물(b_idx)을 DB로부터 가져온다.
        BbsVO vo = bbsService.getBbs(b_idx);

        //vo를 list에서 찾아보자
        boolean chk = false; // false가 계속 유지된다면 조회수 증가
        for(BbsVO v : list) {
            if(v.getB_idx().equals(b_idx)) {
                chk = true; // 이미 읽은 게시물이다.
                break;
            }
        }

        //chk가 false를 유지하고 있다면 한번도 읽지 않은 게시물을 의미한다.
        // 그러므로 조회수를 증가시키자
        if(!chk){
            bbsService.hit(b_idx); //조회수 증가!(DB에 반영)

            // 화면에 즉각적으로 반영하기 위해서 먼저
            // 조회수를 얻어낸다.
            String hit = vo.getHit(); //vo에 String형으로 저장되어있음 그래서 정수형으로 변경 필요
            int h = Integer.parseInt(hit)+1; //1증가

            vo.setHit(String.valueOf(h)); //vo에 다시 저장
            //이렇게 hit라는 변수를 연산처리 한다면
            //처음부터 vo의 hit라는 변수는 int형으로 선언하는 것을 권장한다!

            //읽은 게시물로 등록하자
            list.add(vo);
        }
        mv.addObject("bname", bname);
        mv.addObject("cPage", cPage);
        mv.addObject("vo", vo);
        mv.setViewName(bname+"/view");
        return mv;
    }
    
    @PostMapping("/edit")
    public ModelAndView edit(BbsVO vo, String cPage) {
        ModelAndView mv = new ModelAndView();
        
        String c_type = request.getContentType();
        if (c_type != null && c_type.startsWith("multipart/")) {
            // 파일이 첨부되는 폼에서 호출된 경우
            MultipartFile f = vo.getFile(); //파일이름 뽑기
            if (f.getSize() > 0) {
                String realPath = application.getRealPath(bbs_upload);
                String oname = f.getOriginalFilename();
                String fname = FileRenameUtil.checkSameFileName(oname, realPath);
                try {
                    f.transferTo(new File(realPath, fname));
                    vo.setFile_name(fname);
                    vo.setOri_name(oname);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
                vo.setIp(request.getRemoteAddr()); //IP저장
                bbsService.edit(vo); //db에서 수정처리
                
                mv.setViewName("redirect:view?bname="+vo.getBname()
                        +"&b_idx="+vo.getB_idx()+"&cPage="+cPage);
                
            } else {
                //인자로 받은 b_idx를 가지고 게시물을 얻어온다.
                BbsVO bvo = bbsService.getBbs(vo.getB_idx());
            
                mv.addObject("bname", vo.getBname());
                mv.addObject("cPage", cPage);
                mv.addObject("vo", bvo);
                mv.setViewName(vo.getBname()+"/edit");
            }
            return mv;
        }


    @PostMapping("download")
    public ResponseEntity<String> download(String f_name) {
        String realPath = application.getRealPath(bbs_upload+f_name);
        File f = new File(realPath);

        if (f.exists()) {
            //파일이 존재한다면
            //파일다운로드 처리를 해주자
            byte[] buf = new byte[4096];
            int size = -1;

            //파일을 다운로드에 필요한 스트림들 준비
            BufferedInputStream bis = null;
            FileInputStream fis = null;

            BufferedOutputStream bos = null;
            ServletOutputStream sos = null;
            try {
                response.setContentType("application/x-msdownload");
                response.setHeader("Content-Disposition", 
                "attachment; filename=" + 
                new String(f_name.getBytes(), "8859_1"));

                fis = new FileInputStream(f);
                bis = new BufferedInputStream(fis);

                sos = response.getOutputStream();
                bos = new BufferedOutputStream(sos);

                while((size = bis.read(buf)) != -1) {
                    bos.write(buf, 0, size);
                    bos.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bos != null) bos.close();
                    if (sos != null) sos.close();
                    if (bis != null) bis.close();
                    if (fis != null) fis.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

        }
        
        return null;
    }
    
    
}
