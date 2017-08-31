<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<% String root = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><decorator:title /></title>
<link rel="stylesheet" href="<%= root %>/css/admin/style.css">
<!--[if lt IE 9]>
<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<script type="text/javascript" src="<%= root %>/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="<%= root %>/js/jquery-1.9.1.min.js"></script>
<decorator:head />
</head>
<body>
	<div id="wrapper">
		<header id="header">
			<a href="<%= root %>/admin/main.do">
				<img src="<%= root %>/images/confession.jpg" alt="DJ SCREAM">
			</a>
		</header>
		<div id="body_wrapper">	
			<nav id="navigator">
				<h1 class="hidden">관리자 메뉴</h1>
				<ul>
					<li><a href="<%= root %>/admin/member/listMember.do">회원 관리</a></li>
					<li><a href="<%= root %>/admin/board/listBoard.do">개시판 관리</a></li>
					<li><a href="<%= root %>/admin/music/listMusic.do">으악개시판 관리</a></li>
					<li><a href="<%= root %>/admin/cover/listCover.do">커버스토리 관리</a></li>
					<li><a href="<%= root %>/admin/stats/statistics.do">통계</a></li>
					<li><a href="<%= root %>/user/main.do">메인 페이지</a></li>
				</ul>
			</nav>
			<section id="contents">
				<decorator:body />
			</section>
		</div>
		<footer id="footer">
			<p>Copyright &copy; 2013~2014 by migry. All Rights Reserved.</p>
		</footer>
	</div>
</body>
</html>