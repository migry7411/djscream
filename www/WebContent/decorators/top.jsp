<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="net.migry.dto.BoardCode, net.migry.dao.BoardCodeDAO, java.util.*" %>
<% 
	String root = request.getContextPath();
	
	BoardCodeDAO dao = new BoardCodeDAO();
	List<BoardCode> list = dao.selectBoardCode("U");
%>
				<ul class="main_menu">
					<li><a href="<%= root %>/user/main.do" onmouseover="getSubMenu(0)">HOME</a></li>
					<li><a href="<%= root %>/user/about.do" onmouseover="getSubMenu(0)">ABOUT</a></li>
					<li>
						<a href="#" onmouseover="getSubMenu(1)">MUSIC</a>
						<ul id="sub_music">
							<li><a href="#">가요</a></li>
							<li><a href="#">팝송</a></li>
							<li><a href="#">클래식</a></li>
							<li><a href="#">기타</a></li>
						</ul>
					</li>
					<li>
						<a href="#" onmouseover="getSubMenu(2)">BOARD</a>
						<ul id="sub_board">
<%
	for(BoardCode code : list) {
%>
							<li><a href="<%= root %>/user/board/listBoard.do?code=<%= code.getId() %>"><%= code.getName() %></a></li>
<%
	}
%>
						</ul>
					</li>
				</ul>