package net.migry.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.migry.common.NumPerPage;
import net.migry.common.Utility;
import net.migry.dao.CoverStoryDAO;
import net.migry.dto.CoverStory;
import net.migry.dto.Member;
import net.migry.dto.PageInfo;
import net.migry.dto.Search;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CoverStoryController {
	private static CoverStoryDAO dao = null;
	
	static {
		dao = new CoverStoryDAO();
	}
	
	public CoverStoryController() {
		super();
	}
	
	@RequestMapping("/admin/cover/listCover.do")
    public ModelAndView listMember(HttpServletRequest request) {
		ModelAndView mv  = new ModelAndView();
		int beginPerPage = 0;		// 시작 레코드 번호
		int numPerPage = NumPerPage.COVER_STORY;		// 한 페이지당 보여줄 레코드 개수
		//int recNo = 0;					// 개시판 출력 글번호
		
        try {
        	PageInfo pageinfo = Utility.getPageInfo(request);
        	
        	// 시작 레코드번호 값 부여
        	beginPerPage = pageinfo.getNowPage() * numPerPage;
        	
        	int startRow = beginPerPage + 1;
    		int endRow = beginPerPage + numPerPage;
    		
        	Search s = new Search(pageinfo.getSearchColumn(), pageinfo.getSearchWord(), startRow, endRow, pageinfo.getCode());
        	List<CoverStory> list = dao.selectCover(s);
        	int count = dao.countCover();
        	String url = "listCover.do";
        	String paging = Utility.paging(pageinfo.getSearchColumn(), pageinfo.getSearchWord(), pageinfo.getNowPage(), pageinfo.getNowBlock(),
        						count, numPerPage, url, pageinfo.getCode());
        	
        	Member loginMember = (Member)(request.getSession().getAttribute("login"));
            
            if(loginMember == null) {
            	mv.setViewName("/common/login");
            	mv.addObject("flag", "U");
            } else if (!loginMember.getUserid().equals("administrator")) {
            	mv.setViewName("/common/login");
            	mv.addObject("flag", "A");
            } else {
	        	mv.setViewName("/admin/cover/list");
	        	mv.addObject("list", list);
	        	mv.addObject("count", list.size());
	        	mv.addObject("pageinfo", pageinfo);
	        	mv.addObject("paging", paging);
            }
        } catch (Exception ex) {
        	System.out.println("Cover Story List 에러 : " + ex.toString());
        	ex.printStackTrace();
        	mv.setViewName("/common/error");
        }
        
        return mv;
    }
	
	@RequestMapping(value = "/admin/cover/insertCover.do", method = RequestMethod.POST )
	public String insertCover(@ModelAttribute CoverStory dto, HttpServletRequest request) {
		try {
			Member loginMember = (Member)(request.getSession().getAttribute("login"));
			
			if(loginMember == null) {
				return "redirect:/common/login.do?flag=U";
	        } else if (!loginMember.getUserid().equals("administrator")) {
	        	return "redirect:/common/login.do?flag=A";
	        } else {
	        	dto.setId(dao.getNewID());
	        	dao.insertCover(dto);
	           	return "redirect:listCover.do";
	        }
		} catch(Exception ex) {
			System.out.println("Cover Story Insert 에러 : " + ex.toString());
        	ex.printStackTrace();
        	return "redirect:/common/error.do";
		}
	}
	
	@RequestMapping("/admin/cover/deleteCover.do" )
	public String deleteCover(HttpServletRequest request) {
		try {
			Member loginMember = (Member)(request.getSession().getAttribute("login"));
			
			if(loginMember == null) {
				return "redirect:/common/login.do?flag=U";
	        } else if (!loginMember.getUserid().equals("administrator")) {
	        	return "redirect:/common/login.do?flag=A";
	        } else {
				int id = Integer.parseInt(request.getParameter("id"));
				dao.deleteCover(id);
				return "redirect:listCover.do";
	        }
		} catch(Exception ex) {
			System.out.println("Cover Story Delete 에러 : " + ex.toString());
        	ex.printStackTrace();
        	return "redirect:/common/error.do";
		}
	}
}
