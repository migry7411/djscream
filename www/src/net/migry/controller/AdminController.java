package net.migry.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.migry.common.JsonUtil;
import net.migry.dto.Member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	@RequestMapping("/admin/main.do")
    public ModelAndView adminMain(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Member loginMember = (Member)(request.getSession().getAttribute("login"));
        
        if(loginMember == null) {
        	mv.setViewName("/common/login");
        	mv.addObject("flag", "U");
        } else if (!loginMember.getUserid().equals("administrator")) {
        	mv.setViewName("/common/login");
        	mv.addObject("flag", "A");
        } else {
        	mv.setViewName("/admin/main");
        }
        
        return mv;
    }
	
	@RequestMapping("/admin/login.do")
    public void loginFilter(HttpServletRequest request, HttpServletResponse response) {
        Member loginMember = (Member)(request.getSession().getAttribute("login"));
        Map<String, Object> map = new HashMap<String, Object>();
        
        if(loginMember == null) {
        	map.put("flag", "1");
        } else if (!loginMember.getUserid().equals("administrator")) {
        	map.put("flag", "2");
        } else {
        	map.put("flag", "0");
        }
        
        JsonUtil.parseJSON(map, response);
    }
}
