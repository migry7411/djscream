package net.migry.common;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import net.migry.dto.PageInfo;

public class Utility {
	public static String paging(String col, String word, int nowPage,int nowBlock,int totalRecord,int numPerPage,String url,String code){
    	        int total_page = 0;    //전체 페이지 수       
    	        int total_block = 0;   //전체 블럭 수
    	        int page_per_block =0; //블럭당 보여줄  페이지 수(기본값은 10페이지)    
    	        String pre_link="";    // 이전 링크 <a href="">이전10개</a>
    	        String now_link="";    // 현재 링크 12345678910
    	        String next_link="";   // 다음 링크 <a href="">다음10개</a>
    	        String link_str="";    // 전체 페이징 링크
    	        
    	        /* 전체페이지수 산출, 레코드 수/페이지당 레코드 수
    	         ceil(451/10) = ceil(45.1) = 46*/
    	        total_page =(int)Math.ceil((double)totalRecord / numPerPage);

    	        //한 페이지 블럭당 페이지의 수
    	        //[이전10개]1,2,3,4,5,6,7,8,9,10[다음10개]
    	        page_per_block =10;
    	      
    	        //현재블럭을 가져옴, 최초 로딩시는 처음 0임
    	        //전체 페이지 블럭(10개씩 묶어서)
    	        //ceil(41/10) = ceil(4.1) = 5             

    	        //1블럭: 1,2,3,4,5,6,7,8,9,10
    	        //2블럭: 11,12,13,14,15,16,17,18,19,20
    	        total_block =(int)Math.ceil((double)total_page / page_per_block);
    	    
    	         
    	        // 1페이지: "", "",0, 10
    	        // 2페이지: "", "",10, 10
    	        // 3페이지: "", "",20, 10
    	        
    	        // 페이징 시작
    	        if(totalRecord != 0){
    	            if(nowBlock > 0){ //1블럭이상 -> 11page 이상
    	                //이전 10개 링크, 이전 블럭으로 이동
    	                pre_link = "[<a href=./"+url;
    	                pre_link = pre_link + "?nowBlock=" + (nowBlock-1);
    	                pre_link = pre_link + "&nowPage=" + ((nowBlock-1)*page_per_block);
    	                pre_link = pre_link + "&searchColumn=" + col;
    	                pre_link = pre_link + "&searchWord=" + word;
    	                pre_link = pre_link + "&code=" + code +">이전"+ page_per_block+"개</a>]";
    	            }
    	            for (int i = 0; i < page_per_block; i++) {
    	                // 현재 블럭당 페이지 링크 
    	                now_link=now_link + " [<a href=./"+url;
    	                now_link=now_link + "?nowBlock=" + nowBlock;
    	                now_link=now_link + "&nowPage=" + ((nowBlock*page_per_block) + i);
    	                now_link=now_link + "&searchColumn=" + col;
    	                now_link=now_link + "&searchWord=" + word ;
    	                now_link=now_link + "&code="+ code +">";
    	                now_link=now_link + ((nowBlock * page_per_block) + i + 1)+"</a>]";
    	                //마지막 페이지이면 페이지 번호 출력을 종료
    	                if ((nowBlock * page_per_block) + i + 1 == total_page)  break;
    	            }
    	            if (total_block > nowBlock + 1) {
    	                // 다음 블럭으로 이동 링크
    	                next_link = " [<a href=./"+url+"?nowBlock="+(nowBlock + 1);
    	                next_link = next_link + "&nowPage="+((nowBlock + 1) * page_per_block);
    	                next_link = next_link + "&searchColumn="+col;
    	                next_link = next_link + "&searchWord="+word;
    	                next_link = next_link + "&code="+ code +">다음 "+page_per_block+"개</a>]";
    	            } 
    	        }
    	        // 이전 10개 ::: 1 2 3 4 5 6 7 8 9 10 ::: 다음 10개
    	        link_str=pre_link + now_link + next_link; 
    	            
    	        return link_str;
    	    }
    
    public static String getContent(String comment){
    	String str;
    	
    	str = comment.replaceAll("<", "&lt;");
    	str = comment.replaceAll(">", "&gt;");
    	str = comment.replaceAll("\r\n", "<br />");
        
    	return str;
    }
    
    public static String reply_paging(int nowPage,int nowBlock,int totalRecord,int numPerPage,int num){
        int total_page = 0;    //전체 페이지 수       
        int total_block = 0;   //전체 블럭 수      
        int page_per_block =0; //블럭당 보여줄  페이지 수(기본값은 10페이지)    
        String pre_link="";    // 이전 링크 <a href="">이전10개</a>
        String now_link="";    // 현재 링크 12345678910
        String next_link="";   // 다음 링크 <a href="">다음10개</a>
        String link_str="";    // 전체 페이징 링크
        
        /* 전체페이지수 산출, 레코드 수/페이지당 레코드 수
         ceil(451/10) = ceil(45.1) = 46*/
        total_page =(int)Math.ceil((double)totalRecord / numPerPage);

        //한 페이지 블럭당 페이지의 수
        //[이전10개]1,2,3,4,5,6,7,8,9,10[다음10개]
        page_per_block =10;
      
        //현재블럭을 가져옴, 최초 로딩시는 처음 0임
        //전체 페이지 블럭(10개씩 묶어서)
        //ceil(41/10) = ceil(4.1) = 5             

        //1블럭: 1,2,3,4,5,6,7,8,9,10
        //2블럭: 11,12,13,14,15,16,17,18,19,20
        total_block =(int)Math.ceil((double)total_page / page_per_block);
    
         
        // 1페이지: "", "",0, 10
        // 2페이지: "", "",10, 10
        // 3페이지: "", "",20, 10
        
        // 페이징 시작
        if(totalRecord != 0){
            if(nowBlock > 0){ //1블럭이상 -> 11page 이상
                //이전 10개 링크, 이전 블럭으로 이동
                pre_link = "<a href='javascript:listReply(" + (nowBlock-1) + ", ";
                pre_link += ((nowBlock-1)*page_per_block) + ", " + num + ")'>";
                pre_link += "이전"+ page_per_block+"개</a>";
            }
            for (int i = 0; i < page_per_block; i++) {
                // 현재 블럭당 페이지 링크 
            	now_link += " <a href='javascript:listReply(" + nowBlock + ", ";
                now_link += ((nowBlock*page_per_block) + i) + ", " + num + ")'>";
                now_link += ((nowBlock * page_per_block) + i + 1)+"</a>";
                //마지막 페이지이면 페이지 번호 출력을 종료
                if ((nowBlock * page_per_block) + i + 1 == total_page)  break;
            }
            if (total_block > nowBlock + 1) {
                // 다음 블럭으로 이동 링크
            	next_link = " <a href='javascript:listReply(" + (nowBlock + 1) + ", ";
                next_link += ((nowBlock + 1) * page_per_block) + ", " + num + ")'>";
                next_link += "다음 "+page_per_block+"개</a>";
            } 
        }
        // 이전 10개 ::: 1 2 3 4 5 6 7 8 9 10 ::: 다음 10개
        link_str=pre_link+":::"+now_link+":::"+next_link;
        
        return link_str;
    }
        
    public static PageInfo getPageInfo(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("UTF-8");
    	
    	int nowPage = 0;				// 현재 페이지
    	int nowBlock = 0;				// 현재 블록(10페이쥐 단위)
    	
    	String searchColumn = null;			// 검색칼럼(name, subject, content, subject+content)
    	String searchWord = null;				// 검색어
    	
    	String code = request.getParameter("code");
    	
    	if(request.getParameter("nowPage") != null){
    		nowPage = Integer.parseInt(request.getParameter("nowPage"));
    	}
    	
    	if(request.getParameter("nowBlock") != null){
    		nowBlock = Integer.parseInt(request.getParameter("nowBlock"));
    	}
    	
    	if(request.getParameter("searchColumn") != null){
    		searchColumn = request.getParameter("searchColumn");
    	}
    	
    	if(request.getParameter("searchWord") != null){
    		try {
				searchWord = URLDecoder.decode(request.getParameter("searchWord"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
    	}
    	
    	PageInfo pageinfo = new PageInfo(nowPage, nowBlock, searchColumn, searchWord, code);
    	
    	return pageinfo;
    }
}

