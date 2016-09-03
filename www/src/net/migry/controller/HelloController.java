package net.migry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	@RequestMapping("/hello")
    public ModelAndView helloWorld() {
		String message = "메뚜기 월드에 오신걸 환영합니다..."; 
        ModelAndView mv = new ModelAndView();
        
        mv.setViewName("hello");
        mv.addObject("message", message);
        
        return mv;
    }

}
