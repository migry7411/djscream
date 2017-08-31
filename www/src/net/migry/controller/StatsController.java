package net.migry.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.migry.common.JsonUtil;
import net.migry.dao.StatsDAO;
import net.migry.dto.Member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StatsController {
	
	private static StatsDAO dao = null;
	
	static {
		dao = new StatsDAO();
	}

	public StatsController() {
		super();
	}
	
	@RequestMapping("/admin/stats/statistics.do")
	public ModelAndView listBoardCode(HttpServletRequest request) {
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
	        	mv.setViewName("/admin/stats/statistics");
	        }
		} catch(Exception ex) {
			System.out.println("Statistics 에러 : " + ex.toString());
        	ex.printStackTrace();
        	mv.setViewName("/common/error");
		}
		
		return mv;
	}
	
	@RequestMapping("/admin/stats/statsAjax.do")
    public void statsAjax(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Map<String, Object>> list = dao.selectBoardStats();
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", JsonUtil.parseJSONList(list));
        	
        	JsonUtil.parseJSON(map, response);
		} catch(Exception ex) {
			System.out.println("Statistics 에러 : " + ex.toString());
        	ex.printStackTrace();
		}
	}
}
