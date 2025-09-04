package com.sist.ex0904;

import com.sist.ex0904.data.vo.DataVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestControl {

    @GetMapping("/index")
    public ModelAndView index(String code){

        ModelAndView mv = new ModelAndView();
        mv.addObject("code", code);

        mv.setViewName("index");
        return mv;
    }

    @GetMapping("/") // http://localhost:8080
    public String main(Model model, String code){
        // request에 저장된 Model의 주소를 model로 받는다.
        // 스프링이 요청한 객체(Request)로부터 model을 얻어낸다.
        if(code != null){ //파라미터 받은 경우
            DataVO vo = new DataVO();
            vo.setStr(code);
            model.addAttribute("vo", vo);
        }
        //return mv;
        return "test";
    }
}
