package net.migry.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.migry.common.GmailSender;
import net.migry.common.JsonUtil;
import net.migry.common.NumPerPage;
import net.migry.common.Utility;
import net.migry.dao.MemberDAO;
import net.migry.dto.Member;
import net.migry.dto.PageInfo;
import net.migry.dto.Search;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {
	private static MemberDAO dao = null;
	
	static{
		dao = new MemberDAO();
	}
	
	public MemberController() {
		super();
	}
	
	// 회원 목록 조회
	@RequestMapping("/admin/member/listMember.do")
    public ModelAndView listMember(HttpServletRequest request) {
		ModelAndView mv  = new ModelAndView();
		int beginPerPage = 0;		// 시작 레코드 번호
		int numPerPage = NumPerPage.MEMBER_LIST;		// 한 페이지당 보여줄 레코드 개수
		//int recNo = 0;					// 개시판 출력 글번호
		
        try {
        	PageInfo pageinfo = Utility.getPageInfo(request);
        	//System.out.println(pageinfo.getSearchWord());
        	
        	// 시작 레코드번호 값 부여
        	beginPerPage = pageinfo.getNowPage() * numPerPage;
        	
        	int startRow = beginPerPage + 1;
    		int endRow = beginPerPage + numPerPage;
    		
        	Search s = new Search(pageinfo.getSearchColumn(), pageinfo.getSearchWord(), startRow, endRow, pageinfo.getCode());
        	List<Member> list = dao.selectMember(s);
        	int count = dao.countMember(s);
        	String url = "listMember.do";
        	String paging = Utility.paging(pageinfo.getSearchColumn(), pageinfo.getSearchWord(), pageinfo.getNowPage(), pageinfo.getNowBlock(),
        						count, numPerPage, url, pageinfo.getCode());
        	
//        	for(Member m : list) {
//    			String pwd = m.getPassword();
//    			String email = m.getEmail();
//    			
//    			System.out.print(pwd + ", ");
//    			System.out.println(email);
//    		}
        	
        	Member loginMember = (Member)(request.getSession().getAttribute("login"));
            
            if(loginMember == null) {
            	mv.setViewName("/common/login");
            	mv.addObject("flag", "U");
            } else if (!loginMember.getUserid().equals("administrator")) {
            	mv.setViewName("/common/login");
            	mv.addObject("flag", "A");
            } else {
	        	mv.setViewName("/admin/member/list");
	        	mv.addObject("list", list);
	        	mv.addObject("count", list.size());
	        	mv.addObject("pageinfo", pageinfo);
	        	mv.addObject("paging", paging);
            }
        } catch (Exception ex) {
        	System.out.println("Member List 에러 : " + ex.toString());
        	ex.printStackTrace();
        	mv.setViewName("/common/error");
        }
        
        return mv;
    }
	
	// 회원 수정 (사용자 화면)
	@RequestMapping("/user/member/updateMember.do")
    public ModelAndView updateMember(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv  = new ModelAndView();
		
        try {
        	Member loginMember = (Member)(request.getSession().getAttribute("login"));
        	String id = request.getParameter("userid");
            
            if(loginMember == null) {
            	mv.setViewName("/common/login");
            	mv.addObject("flag", "U");
            } else if(!loginMember.getUserid().equals(id)) {
            	mv.setViewName("/common/login");
            	mv.addObject("flag", "F");
            } else {
	        	Member member = dao.selectMemberOne(id);
	        	
	        	mv.setViewName("/user/member/update");
	        	mv.addObject("member", member);
            }
        } catch (Exception ex) {
        	System.out.println("Member Update 에러 : " + ex.toString());
        	ex.printStackTrace();
        	mv.setViewName("/common/error");
        }
        
        return mv;
    }
	
	// 회원 수정 (관리자 화면)
	@RequestMapping("/admin/member/updateMember.do")
    public ModelAndView updateMemberAdmin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv  = new ModelAndView();
		
        try {
        	Member loginMember = (Member)(request.getSession().getAttribute("login"));
            
            if(loginMember == null) {
            	mv.setViewName("/common/login");
            	mv.addObject("flag", "U");
            } else if (!loginMember.getUserid().equals("administrator")) {
            	mv.setViewName("/common/login");
            	mv.addObject("flag", "A");
            } else {
	        	String id = request.getParameter("userid");
	        	PageInfo pageinfo = Utility.getPageInfo(request);
	        	Member member = dao.selectMemberOne(id);
	        	
	        	mv.setViewName("/admin/member/update");
	        	mv.addObject("member", member);
	        	mv.addObject("pageinfo", pageinfo);
            }
        } catch (Exception ex) {
        	System.out.println("Member Update 에러 : " + ex.toString());
        	ex.printStackTrace();
        	mv.setViewName("/common/error");
        }
        
        return mv;
    }
	
	// 회원 가입
	@RequestMapping("/user/member/insertMember.do")
	public ModelAndView insertMember() {
		ModelAndView mv  = new ModelAndView();
		
		try {
        	mv.setViewName("/user/member/insert");
		} catch(Exception ex) {
			System.out.println("Member Insert 에러 : " + ex.toString());
        	ex.printStackTrace();
        	mv.setViewName("/common/error");
		}
		
		return mv;
	}
	
	// 회원 가입 처리
	@RequestMapping(value = "/user/member/insertProc.do", method = RequestMethod.POST )
	public ModelAndView insertMemberProc(@ModelAttribute Member dto) {
		ModelAndView mv  = new ModelAndView();
		
		try {
			dao.insertMember(dto);
        	mv.setViewName("/user/member/registComplete");
		} catch(Exception ex) {
			System.out.println("Member Insert Proc 에러 : " + ex.toString());
        	ex.printStackTrace();
        	mv.setViewName("/common/error");
		}
		
		return mv;
	}
	
	// 회원 수정 처리 (사용자 화면)
	@RequestMapping(value = "/user/member/updateProc.do", method = RequestMethod.POST )
	public ModelAndView updateMemberProc(@ModelAttribute Member dto, HttpServletRequest request) {
		ModelAndView mv  = new ModelAndView();
		
		try {
			Member loginMember = (Member)(request.getSession().getAttribute("login"));
	        
	        if(loginMember == null) {
	        	mv.setViewName("/common/login");
	        	mv.addObject("flag", "U");
	        } else if(!loginMember.getUserid().equals(dto.getUserid())) {
            	mv.setViewName("/common/login");
            	mv.addObject("flag", "F");
	        } else {
				String id = dto.getUserid();
	        	Member member = dao.selectMemberOne(id);
	        	mv.setViewName("/user/member/updateComplete");
	        	
	        	String realPasswd = member.getPassword().trim();
	        	String passwd = dto.getPassword().trim();
	        	
	        	if(passwd.equals(realPasswd)) {
	        		dao.updateMember(dto);
	        		mv.addObject("checkPasswd", "Y");
	        	} else {
	        		mv.addObject("checkPasswd", "N");
	        	}
	        }
		} catch(Exception ex) {
			System.out.println("Member Update Proc 에러 : " + ex.toString());
        	ex.printStackTrace();
        	mv.setViewName("/common/error");
		}
		
		return mv;
	}
	
	// 회원 수정 처리 (관리자 화면)
	@RequestMapping(value = "/admin/member/updateProc.do", method = RequestMethod.POST )
	public ModelAndView updateMemberProcAdmin(@ModelAttribute Member dto, HttpServletRequest request) {
		ModelAndView mv  = new ModelAndView();
		
		try {
			Member loginMember = (Member)(request.getSession().getAttribute("login"));
		        
	        if(loginMember == null) {
	        	mv.setViewName("/common/login");
	        	mv.addObject("flag", "U");
	        } else if (!loginMember.getUserid().equals("administrator")) {
	        	mv.setViewName("/common/login");
	        	mv.addObject("flag", "A");
	        } else {
				PageInfo pageinfo = Utility.getPageInfo(request);
				String id = dto.getUserid();
	        	Member member = dao.selectMemberOne(id);
	        	
	        	mv.setViewName("/admin/member/updateComplete");
	        	mv.addObject("pageinfo", pageinfo);
	        	
	        	String realPasswd = member.getPassword().trim();
	        	String passwd = dto.getPassword().trim();
	        	
	        	if(passwd.equals(realPasswd)) {
	        		dao.updateMember(dto);
	        		mv.addObject("checkPasswd", "Y");
	        	} else {
	        		mv.addObject("checkPasswd", "N");
	        	}
	        }
		} catch(Exception ex) {
			System.out.println("Member Update Proc 에러 : " + ex.toString());
        	ex.printStackTrace();
        	mv.setViewName("/common/error");
		}
		
		return mv;
	}
	
	// 회원 탈퇴
	@RequestMapping("/user/member/deleteMember.do")
	public ModelAndView deleteMember(HttpServletRequest request) {
		ModelAndView mv  = new ModelAndView();
		
		try {
			Member loginMember = (Member)(request.getSession().getAttribute("login"));
			String id = request.getParameter("userid");
	        
	        if(loginMember == null) {
	        	mv.setViewName("/common/login");
	        	mv.addObject("flag", "U");
	        } else if(!loginMember.getUserid().equals(id)) {
            	mv.setViewName("/common/login");
            	mv.addObject("flag", "F");
	        } else {
	        	mv.setViewName("/user/member/delete");
	        	mv.addObject("userid", id);
		    }
		} catch(Exception ex) {
			System.out.println("Member Delete 에러 : " + ex.toString());
        	ex.printStackTrace();
        	mv.setViewName("/common/error");
		}
		
		return mv;
	}
	
	// 회원 탈퇴 처리
	@RequestMapping("/user/member/deleteProc.do")
	public ModelAndView deleteMemberProc(HttpServletRequest request) {
		ModelAndView mv  = new ModelAndView();
		String flag = "N";
		
		try {
			Member loginMember = (Member)(request.getSession().getAttribute("login"));
			String id = request.getParameter("userid");
			
	        if(loginMember == null) {
	        	mv.setViewName("/common/login");
	        	mv.addObject("flag", "U");
	        } else if(!loginMember.getUserid().equals(id)) {
            	mv.setViewName("/common/login");
            	mv.addObject("flag", "F");
	        } else {
				String name = request.getParameter("username");
				String passwd = request.getParameter("password");
				String email = request.getParameter("email");
				
				Member m = dao.selectMemberOne(id);
				
				if(m.getUsername().equals(name) && m.getPassword().equals(passwd) && m.getEmail().equals(email)) {
					dao.deleteMember(id);
					flag = "Y";
				}
				
	        	mv.setViewName("/user/member/deleteComplete");
	        	mv.addObject("flag", flag);
	        }
		} catch(Exception ex) {
			System.out.println("Member Delete Proc 에러 : " + ex.toString());
        	ex.printStackTrace();
        	mv.setViewName("/common/error");
		}
		
		return mv;
	}
	
	// 회원 삭제 처리
	@RequestMapping("/admin/member/deleteProc.do")
	public ModelAndView deleteMemberProcAdmin(HttpServletRequest request) {
		ModelAndView mv  = new ModelAndView();
		
		try {
			Member loginMember = (Member)(request.getSession().getAttribute("login"));
	        
	        if(loginMember == null) {
	        	mv.setViewName("/common/login");
	        	mv.addObject("flag", "U");
	        } else if (!loginMember.getUserid().equals("administrator")) {
	        	mv.setViewName("/common/login");
	        	mv.addObject("flag", "A");
	        } else {
				String id = request.getParameter("userid");
				PageInfo pageinfo = Utility.getPageInfo(request);
				dao.deleteMember(id);
				
	        	mv.setViewName("/admin/member/deleteComplete");
	        	mv.addObject("pageinfo", pageinfo);
	        }
		} catch(Exception ex) {
			System.out.println("Member Delete Proc 에러 : " + ex.toString());
        	ex.printStackTrace();
        	mv.setViewName("/common/error");
		}
		
		return mv;
	}
	
	// 회원 아이디 체크
	@RequestMapping("/user/member/checkId.do")
    public void checkId(HttpServletRequest request, HttpServletResponse response) {
        try {
        	String id = request.getParameter("userid");
        	String value = dao.checkMember("USERID", id);
        	
        	Map<String, Object> map = new HashMap<String, Object>();
        	map.put("userid", value == null ? "" : value);
        	
        	JsonUtil.parseJSON(map, response);
        } catch (Exception ex) {
        	System.out.println("Member Check ID 에러 : " + ex.toString());
        	ex.printStackTrace();
        }
    }
	
	// 회원 닉네임 체크
	@RequestMapping("/user/member/checkNickname.do")
    public void checkNickname(HttpServletRequest request, HttpServletResponse response) {
        try {
        	String nickname = request.getParameter("nickname");
        	String value = dao.checkMember("NICKNAME", nickname);
        	
        	Map<String, Object> map = new HashMap<String, Object>();
        	map.put("userid", value == null ? "" : value);
        	
        	JsonUtil.parseJSON(map, response);
        } catch (Exception ex) {
        	System.out.println("Member Check Nickname 에러 : " + ex.toString());
        	ex.printStackTrace();
        }
    }
	
	// 회원 이메일 체크
	@RequestMapping("/user/member/checkEmail.do")
    public void checkEmail(HttpServletRequest request, HttpServletResponse response) {
        try {
        	String email = request.getParameter("email");
        	String value = dao.checkMember("NICKNAME", email);
        	
        	Map<String, Object> map = new HashMap<String, Object>();
        	map.put("userid", value == null ? "" : value);
        	
        	JsonUtil.parseJSON(map, response);
        } catch (Exception ex) {
        	System.out.println("Member Check Email 에러 : " + ex.toString());
        	ex.printStackTrace();
        }
    }
	
	// 아이디 찾기
	@RequestMapping("/user/member/findId.do")
	public ModelAndView findId() {
		ModelAndView mv  = new ModelAndView();
		
		try {
			mv.setViewName("/user/member/findId");
		} catch(Exception ex) {
			System.out.println("Member Find ID 에러 : " + ex.toString());
			ex.printStackTrace();
			mv.setViewName("/common/error");
		}
		
		return mv;
	}
	
	// 패스워드 찾기
	@RequestMapping("/user/member/findPasswd.do")
	public ModelAndView findPasswd() {
		ModelAndView mv  = new ModelAndView();
		
		try {
			mv.setViewName("/user/member/findPasswd");
		} catch(Exception ex) {
			System.out.println("Member Find Password 에러 : " + ex.toString());
			ex.printStackTrace();
			mv.setViewName("/common/error");
		}
		
		return mv;
	}
	
	// 아이디 찾아서 메일로 전송
	@RequestMapping("/user/member/findIdProc.do")
	public ModelAndView findIdProc(HttpServletRequest request) {
		ModelAndView mv  = new ModelAndView();
		String flag = "N";
		
		try {
			String name = request.getParameter("username");
			String email = request.getParameter("email");
			String id = dao.findMember("USERID", email, "", name);
			
			if(id != null) {
				GmailSender.sendMail("migry7411@gmail.com", email, "DJ SCREAM", "Your ID", "Your ID is " + id);
				flag = "Y";
			}
			
        	mv.setViewName("/user/member/findIdComplete");
        	mv.addObject("flag", flag);
		} catch(Exception ex) {
			System.out.println("Member Find ID Proc 에러 : " + ex.toString());
        	ex.printStackTrace();
        	mv.setViewName("/common/error");
		}
		
		return mv;
	}
	
	// 비밀번호 찾아서 메일로 전송
	@RequestMapping("/user/member/findPasswdProc.do")
	public ModelAndView findPasswdProc(HttpServletRequest request) {
		ModelAndView mv  = new ModelAndView();
		String flag = "N";
		
		try {
			String id = request.getParameter("userid");
			String email = request.getParameter("email");
			String passwd = dao.findMember("PASSWORD", email, "", id);
			
			if(passwd != null) {
				GmailSender.sendMail("migry7411@gmail.com", email, "DJ SCREAM", "Your Password", "Your Password is " + passwd);
				flag = "Y";
			}
			
        	mv.setViewName("/user/member/findPasswdComplete");
        	mv.addObject("flag", flag);
		} catch(Exception ex) {
			System.out.println("Member Find Password Proc 에러 : " + ex.toString());
        	ex.printStackTrace();
        	mv.setViewName("/common/error");
		}
		
		return mv;
	}
}
