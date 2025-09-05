package com.sist.ex0905_ex1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Test1Control {

    // application.properties파일에 있는 변수값을 가져온다.
    @Value("${msg}")
    private String message;

    @GetMapping("/")
    public String index(){
        return "SpringBoot Ex1";
    }

    @GetMapping("/t1")
    //반드시 param이라는 파라미터가 있어야 함!
    public String test1(@RequestParam String param){
        return "반갑습니다. 스프링입니다.-param:"+param;
    }

    @GetMapping("/t2/{var}")
    public String test2(@PathVariable String var){ // 파라미터값을 받아도되고 안받아도 된다.
        return "경로변수:"+var;
    }

    @GetMapping("/t3/{var}")
    public String test3(@PathVariable("var") String param){
        return "경로변수:"+param;
    }

    @GetMapping("/t4")
    public ModelAndView test4(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("str", "쌍용교육센터 이전-선릉역으로 ㅎㅎㅎ");
        mv.addObject("msg", message);

        mv.setViewName("t4");
        return mv;
    }
}
