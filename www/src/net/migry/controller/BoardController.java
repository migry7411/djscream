package net.migry.controller;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import net.migry.common.FileService;
import net.migry.common.JsonUtil;
import net.migry.common.NumPerPage;
import net.migry.common.Utility;
import net.migry.dao.BoardCodeDAO;
import net.migry.dao.BoardDAO;
import net.migry.dto.Board;
import net.migry.dto.BoardCode;
import net.migry.dto.BoardReply;
import net.migry.dto.Member;
import net.migry.dto.PageInfo;
import net.migry.dto.Search;

@Controller
public class BoardController {
	private static BoardDAO dao = null;
	private static BoardCodeDAO codedao = null;
	
	@Autowired
	private MessageSource messageSource;
	
	static {
		dao = new BoardDAO();
		codedao = new BoardCodeDAO();
	}
	
	public BoardController() {
		super();
	}
	
	// 게시판 목록 조회
	@RequestMapping("/user/board/listBoard.do")
    public ModelAndView listBoard(HttpServletRequest request) {
		ModelAndView mv  = new ModelAndView();
		int beginPerPage = 0;		// 시작 레코드 번호
		int numPerPage = NumPerPage.BOARD_LIST;		// 한 페이지당 보여줄 레코드 개수
		int recNo = 0;					// 개시판 출력 글번호
		
        try {
        	PageInfo pageinfo = Utility.getPageInfo(request);
        	//System.out.println(pageinfo.getSearchWord());
        	
        	// 시작 레코드번호 값 부여
        	beginPerPage = pageinfo.getNowPage() * numPerPage;
        	
        	int startRow = beginPerPage + 1;
    		int endRow = beginPerPage + numPerPage;
    		
        	Search s = new Search(pageinfo.getSearchColumn(), pageinfo.getSearchWord(), startRow, endRow, pageinfo.getCode());
        	List<Board> list = dao.selectBoard(s);
        	
        	if(list.size() < 1) {
        		beginPerPage = (pageinfo.getNowPage() - 1) * numPerPage;
            	
            	startRow = beginPerPage + 1;
        		endRow = beginPerPage + numPerPage;
        		
        		s.setStartRow(startRow);
        		s.setEndRow(endRow);
        		
        		list = dao.selectBoard(s);
        	}
        	
        	int count = dao.countBoard(s);
        	String url = "listBoard.do";
        	String paging = Utility.paging(pageinfo.getSearchColumn(), pageinfo.getSearchWord(), pageinfo.getNowPage(), pageinfo.getNowBlock(),
        						count, numPerPage, url, pageinfo.getCode());
        	recNo = count - beginPerPage;
        	
        	BoardCode boardCode = codedao.selectBoardCodeOne(pageinfo.getCode());
        	Member loginMember = (Member)(request.getSession().getAttribute("login"));
            
        	mv.setViewName("/user/board/list");
        	mv.addObject("list", list);
        	mv.addObject("count", list.size());
        	mv.addObject("pageinfo", pageinfo);
        	mv.addObject("paging", paging);
        	mv.addObject("recNo", recNo);
        	mv.addObject("boardCode", boardCode);
        	mv.addObject("login", loginMember);
        } catch (Exception ex) {
        	System.out.println("Board List 에러 : " + ex.toString());
        	ex.printStackTrace();
        	mv.setViewName("/common/error");
        }
        
        return mv;
    }
	
	// 게시판 글 쓰기
	@RequestMapping("/user/board/insertBoard.do")
	public ModelAndView insertBoard(HttpServletRequest request) {
		ModelAndView mv  = new ModelAndView();
		
		try {
			Member loginMember = (Member)(request.getSession().getAttribute("login"));
	        
	        if(loginMember == null) {
	        	mv.setViewName("/common/login");
	        	mv.addObject("flag", "U");
	        } else {
				String code = request.getParameter("code");
				BoardCode boardCode = codedao.selectBoardCodeOne(code);
				
				mv.setViewName("/user/board/insert");
	        	mv.addObject("login", loginMember);
	        	mv.addObject("code", code);
	        	mv.addObject("boardCode", boardCode);
	        }
        } catch (Exception ex) {
        	System.out.println("Board Insert 에러 : " + ex.toString());
        	ex.printStackTrace();
        	mv.setViewName("/common/error");
        }
		
		return mv;
	}
	
	// 게시판 글 쓰기 처리
	@RequestMapping(value = "/user/board/insertProc.do", method = RequestMethod.POST)
	public String insertBoardProc(@ModelAttribute Board dto, HttpServletRequest request) {
		try {			
			Member loginMember = (Member)(request.getSession().getAttribute("login"));
	        
			if(loginMember == null) {
				return "redirect:/common/login.do?flag=U";
	        } else if(dto == null) {
	        	return "redirect:/common/login.do?flag=F";
	        } else if(!loginMember.getUserid().equals(dto.getUserid())) {
	        	return "redirect:/common/login.do?flag=F";
	        } else {
	        	String path = request.getSession().getServletContext().getRealPath(FileService.boardSavePath);
	        	MultipartFile uploadfile = dto.getUploadFile();
	        	
	        	if (uploadfile != null) {
	        		String fileName = uploadfile.getOriginalFilename();
	        		File file = FileService.getFile(path, fileName);
	        		uploadfile.transferTo(file);
	        		
	        		dto.setFilename(fileName);
	        		dto.setFilesize((int)file.length());
	            }
	        	
	        	dto.setId(dao.getNewBoardID());
				dao.insertBoard(dto);
				return "redirect:listBoard.do?code=" + dto.getCode();
	        }
        } catch (Exception ex) {
        	System.out.println("Board Insert Proc 에러 : " + ex.toString());
        	ex.printStackTrace();
        	return "redirect:/common/error.do";
        }
	}
	
	// 게시판 상세 보기
	@RequestMapping("/user/board/detailBoard.do")
	public ModelAndView detailBoard(HttpServletRequest request) {
		ModelAndView mv  = new ModelAndView();
		int width = 0, height = 0;
		
		try {
			PageInfo pageinfo = Utility.getPageInfo(request);
			int id = Integer.parseInt(request.getParameter("id"));
			Board board = dao.selectBoardOne(id);
			
			if(board == null) {
				mv.setViewName("/user/board/error");
	        	mv.addObject("pageinfo", pageinfo);
			} else {
				Member loginMember = (Member)(request.getSession().getAttribute("login"));
				BoardCode boardCode = codedao.selectBoardCodeOne(pageinfo.getCode());
	        	dao.upReadCount(id);		// 조회수 증가
	        	board = dao.selectBoardOne(id);
				
				//Search s = new Search(pageinfo.getSearchColumn(), pageinfo.getSearchWord(), 0, 0, pageinfo.getCode());
				//Board before = dao.selectBoardBeforeAndAfter(s, id, "before");
				//Board after = dao.selectBoardBeforeAndAfter(s, id, "after");
				
				if(board.getFilename() != null && !board.getFilename().equals("")) {
					String path = request.getSession().getServletContext().getRealPath(FileService.boardSavePath);
					Image img = new ImageIcon(path + "/" + board.getFilename()).getImage();
					width = img.getWidth(null); 		//가로 사이즈
					height = img.getHeight(null);		//세로 사이즈
				}
				
				mv.setViewName("/user/board/detail");
	        	mv.addObject("login", loginMember);
	        	mv.addObject("pageinfo", pageinfo);
	        	mv.addObject("board", board);
	        	mv.addObject("boardCode", boardCode);
	        	mv.addObject("contents", Utility.getContent(board.getContents()));
	        	//mv.addObject("before", before);
	        	//mv.addObject("after", after);
	        	mv.addObject("width", width);
	        	mv.addObject("height", height);
			}
        } catch (Exception ex) {
        	System.out.println("Board Detail 에러 : " + ex.toString());
        	ex.printStackTrace();
        	mv.setViewName("/common/error");
        }
		
		return mv;
	}
	
	// 게시판 글 수정
	@RequestMapping("/user/board/updateBoard.do")
	public ModelAndView updateBoard(HttpServletRequest request) {
		ModelAndView mv  = new ModelAndView();
		
		try {
			Member loginMember = (Member)(request.getSession().getAttribute("login"));
			int id = Integer.parseInt(request.getParameter("id"));
			PageInfo pageinfo = Utility.getPageInfo(request);
			Board board = dao.selectBoardOne(id);
	        
			if(board == null) {
				mv.setViewName("/user/board/error");
	        	mv.addObject("pageinfo", pageinfo);
			} else {
		        if(loginMember == null) {
		        	mv.setViewName("/common/login");
		        	mv.addObject("flag", "U");
		        } else if(!loginMember.getUserid().equals(board.getUserid())) {
		        	mv.setViewName("/common/login");
		        	mv.addObject("flag", "F");
		        } else {
					BoardCode boardCode = codedao.selectBoardCodeOne(pageinfo.getCode());
					
					mv.setViewName("/user/board/update");
		        	mv.addObject("login", loginMember);
		        	mv.addObject("pageinfo", pageinfo);
		        	mv.addObject("board", board);
		        	mv.addObject("boardCode", boardCode);
		        }
			}
        } catch (Exception ex) {
        	System.out.println("Board Update 에러 : " + ex.toString());
        	ex.printStackTrace();
        	mv.setViewName("/common/error");
        }
		
		return mv;
	}
	
	// 게시판 글 수정 처리
	@RequestMapping(value = "/user/board/updateProc.do", method = RequestMethod.POST )
	public String updateBoardProc(@ModelAttribute Board dto, HttpServletRequest request) {
		try {
			Member loginMember = (Member)(request.getSession().getAttribute("login"));
	        
			if(loginMember == null) {
				return "redirect:/common/login.do?flag=U";
	        } else if(dto == null) {
	        	return "redirect:/common/login.do?flag=F";
	        } else if(!loginMember.getUserid().equals(dto.getUserid())) {
	        	return "redirect:/common/login.do?flag=F";
	        } else {
	        	String path = request.getSession().getServletContext().getRealPath(FileService.boardSavePath);
	        	MultipartFile uploadfile = dto.getUploadFile();
	        	
	        	if (uploadfile != null) {
	        		if(dto.getFilename() != null && !dto.getFilename().equals("")) {
	        			FileService.deleteFile(path, dto.getFilename());
	        		}
	        		
	        		String fileName = uploadfile.getOriginalFilename();
	        		File file = FileService.getFile(path, fileName);
	        		uploadfile.transferTo(file);
	        		
	        		dto.setFilename(fileName);
	        		dto.setFilesize((int)file.length());
	            }
	        	
				dao.updateBoard(dto);
				
				PageInfo pageinfo = Utility.getPageInfo(request);
				
				String listPage = "listBoard.do?nowPage=" + pageinfo.getNowPage();
				listPage += "&nowBlock=" + pageinfo.getNowBlock();
				listPage += "&searchColumn=" + pageinfo.getSearchColumn();
				listPage += "&searchWord=" + pageinfo.getSearchWord();
				listPage += "&code=" + pageinfo.getCode();
				
				return "redirect:" + listPage;
	        }
        } catch (Exception ex) {
        	System.out.println("Board Update Proc 에러 : " + ex.toString());
        	ex.printStackTrace();
        	return "redirect:/common/error.do";
        }
	}
	
	// 비밀번호 체크
	@RequestMapping("/user/board/checkPasswd.do")
	public void detailBoardCode(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Board board = dao.selectBoardOne(id);
			
			Map<String, Object> map = new HashMap<String, Object>();
        	map.put("passwd", board.getPasswd());
        	
        	JsonUtil.parseJSON(map, response);
		} catch(Exception ex) {
			System.out.println("Board Check Passwd 에러 : " + ex.toString());
        	ex.printStackTrace();
		}
	}
	
	// 게시판 글 삭제
	@RequestMapping( "/user/board/deleteBoard.do")
	public String deleteBoard(HttpServletRequest request) {
		try {
			Member loginMember = (Member)(request.getSession().getAttribute("login"));
			int id = Integer.parseInt(request.getParameter("id"));
			String userid = request.getParameter("userid");
			String filename = request.getParameter("filename");
	        
			if(loginMember == null) {
				return "redirect:/common/login.do?flag=U";
	        } else if(userid == null || userid == "") {
	        	return "redirect:/common/login.do?flag=F";
	        } else if(!loginMember.getUserid().equals(userid)) {
	        	return "redirect:/common/login.do?flag=F";
	        } else {
	        	if(filename != null && !filename.equals("")) {
	        		String path = request.getSession().getServletContext().getRealPath(FileService.boardSavePath);
        			FileService.deleteFile(path, filename);
        		}
	        	
				dao.deleteBoard(id);
				
				PageInfo pageinfo = Utility.getPageInfo(request);
				
				String listPage = "listBoard.do?nowPage=" + pageinfo.getNowPage();
				listPage += "&nowBlock=" + pageinfo.getNowBlock();
				listPage += "&searchColumn=" + pageinfo.getSearchColumn();
				listPage += "&searchWord=" + pageinfo.getSearchWord();
				listPage += "&code=" + pageinfo.getCode();
				
				return "redirect:" + listPage;
	        }
        } catch (Exception ex) {
        	System.out.println("Board Delete Proc 에러 : " + ex.toString());
        	ex.printStackTrace();
        	return "redirect:/common/error.do";
        }
	}
	
	// 게시판 댓글 목록 조회
	@RequestMapping("/user/board/listBoardReply.do")
    public void listBoardReply(HttpServletRequest request, HttpServletResponse response) {
		int beginPerPage = 0;		// 시작 레코드 번호
		int numPerPage = NumPerPage.BOARD_REPLY;		// 한 페이지당 보여줄 레코드 개수
		
        try {
        	PageInfo pageinfo = Utility.getPageInfo(request);
        	
        	// 시작 레코드번호 값 부여
        	beginPerPage = pageinfo.getNowPage() * numPerPage;
        	
        	int startRow = beginPerPage + 1;
    		int endRow = beginPerPage + numPerPage;
    		
        	Search s = new Search("", "", startRow, endRow, pageinfo.getCode());
        	List<BoardReply> list = dao.selectBoardReply(s);
        	
        	if(list.size() < 1) {
        		beginPerPage = (pageinfo.getNowPage() - 1) * numPerPage;
            	
            	startRow = beginPerPage + 1;
        		endRow = beginPerPage + numPerPage;
        		
        		s.setStartRow(startRow);
        		s.setEndRow(endRow);
        		
        		list = dao.selectBoardReply(s);
        	}
        	
        	int count = dao.countBoardReply(pageinfo.getCode());
        	String url = "listBoardReply.do";
        	String paging = Utility.reply_paging(pageinfo.getNowPage(), pageinfo.getNowBlock(), count, numPerPage, url, pageinfo.getCode());
        	
        	Member loginMember = (Member)(request.getSession().getAttribute("login"));
        	String loginId = "";
        	List<Map<String, Object>> jsonList = JsonUtil.toMapList(list);
        	
        	if(loginMember != null) {
        		loginId = loginMember.getUserid();
        	}
        	
        	Map<String, Object> map = new HashMap<String, Object>();
        	map.put("list", JsonUtil.parseJSONList(jsonList));
        	map.put("paging", paging);
        	map.put("loginid", loginId);
        	map.put("msgList", messageSource.getMessage("contents.reply.list", null, null));
        	map.put("msgEmpty", messageSource.getMessage("messages.common.list.empty", null, null));
        	
        	JsonUtil.parseJSON(map, response);
        } catch (Exception ex) {
        	System.out.println("Board Reply List 에러 : " + ex.toString());
        	ex.printStackTrace();
        }
    }
	
	// 게시판 댓글 쓰기 처리
	@RequestMapping(value = "/user/board/replyProc.do", method = RequestMethod.POST)
	public void insertBoardReplyProc(@ModelAttribute BoardReply dto, HttpServletRequest request, HttpServletResponse response) {
		try {			
			Member loginMember = (Member)(request.getSession().getAttribute("login"));
	        
			if(loginMember == null) {
				return;
	        } else if(dto == null) {
	        	return;
	        } else if(!loginMember.getUserid().equals(dto.getUserid())) {
	        	return;
	        } else {
	        	dto.setId(dao.getNewBoardReplyID());
				dao.insertBoardReply(dto);
				
				Map<String, Object> map = new HashMap<String, Object>();
	        	map.put("boardid", dto.getBoardid());
	        	
	        	JsonUtil.parseJSON(map, response);
	        }
        } catch (Exception ex) {
        	System.out.println("Board Reply Insert Proc 에러 : " + ex.toString());
        	ex.printStackTrace();
        }
	}
}
