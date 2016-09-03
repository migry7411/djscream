package net.migry.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.migry.common.JsonUtil;
import net.migry.dao.MusicCodeDAO;
import net.migry.dto.Member;
import net.migry.dto.MusicCode;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MusicCodeController {
	private static MusicCodeDAO dao = null;
	
	static{
		dao = new MusicCodeDAO();
	}
	
	public MusicCodeController() {
		super();
	}
	
	@RequestMapping("/admin/music/listMusic.do")
	public ModelAndView listMusicCode(HttpServletRequest request) {
		ModelAndView mv  = new ModelAndView();
		
		try{
			Member loginMember = (Member)(request.getSession().getAttribute("login"));
	        
	        if(loginMember == null) {
	        	mv.setViewName("/common/login");
	        	mv.addObject("flag", "U");
	        } else if (!loginMember.getUserid().equals("administrator")) {
	        	mv.setViewName("/common/login");
	        	mv.addObject("flag", "A");
	        } else {
				List<MusicCode> list = dao.selectMusicCode("A");
				mv.setViewName("/admin/music/list");
	        	mv.addObject("list", list);
	        	mv.addObject("count", list.size());
	        }
		} catch(Exception ex) {
			System.out.println("Music Code List 에러 : " + ex.toString());
        	ex.printStackTrace();
        	mv.setViewName("/common/error");
		}
		
		return mv;
	}
	
	@RequestMapping(value = "/admin/music/saveMusic.do", method = RequestMethod.POST )
	public String saveMusicCode(@ModelAttribute MusicCode dto, HttpServletRequest request) {
		try{
			Member loginMember = (Member)(request.getSession().getAttribute("login"));
			
			if(loginMember == null) {
				return "redirect:/common/login.do?flag=U";
	        } else if (!loginMember.getUserid().equals("administrator")) {
	        	return "redirect:/common/login.do?flag=A";
	        } else {
				if(dto.getId() == "") {
					dto.setId(dao.getNewID());
					dao.insertMusicCode(dto);
				} else {
					dao.updateMusicCode(dto);
				}
				
				return "redirect:listMusic.do";
	        }
		} catch(Exception ex) {
			System.out.println("Music Code Save 에러 : " + ex.toString());
        	ex.printStackTrace();
        	return "redirect:/common/error.do";
		}
	}
	
	@RequestMapping("/admin/music/detailMusicCode.do")
	public void detailMusicCode(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			MusicCode musicCode = dao.selectMusicCodeOne(id);
			
			Map<String, Object> map = new HashMap<String, Object>();
        	map.put("id", musicCode.getId());
        	map.put("name", musicCode.getName());
        	map.put("visible", musicCode.getVisible());
        	
        	JsonUtil.parseJSON(map, response);
		} catch(Exception ex) {
			System.out.println("Music Code Detail 에러 : " + ex.toString());
        	ex.printStackTrace();
		}
	}
}
