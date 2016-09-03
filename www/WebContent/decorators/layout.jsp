<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String root = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DJ SCREAM</title>
<meta name="keywords" content="DJ SCREAM, Music">
<meta name="description" content="음악 관련 사이트">
<link rel="stylesheet" href="<%= root %>/css/user/style.css">
<link rel="icon" href="<%= root %>/images/djscream.ico"  type="image/x-icon">
<!--[if lt IE 9]>
<script type="text/javascript" src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<script type="text/javascript" src="<%= root %>/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="<%= root %>/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%= root %>/js/menu.js"></script>
<script type="text/javascript" src="<%= root %>/js/modal.js"></script>
<decorator:head />
</head>
<body>
	<div id="wrapper">
		<header id="header">
			<h1 class="logo" >
				<a href="<%= root %>/user/main.do">DJ SCREAM</a>
			</h1>
			<nav>
				<h1 class="hidden">메뉴</h1>
				<jsp:include page="top.jsp" />
			</nav>
		</header>
		<section id="contents">
			<div class="column">
				<jsp:include page="left.jsp" />
			</div>
			<div class="column">
				<decorator:body />
			</div>
		</section>
		<footer id="footer">
			<ul>
				<li><a href="#" title="트위터"><span>트위터</span></a></li>
				<li><a href="#" title="페이스북"><span>페이스북</span></a></li>
				<li>Copyright &copy; by migry. All Rights Reserved.</li>
			</ul>
		</footer>
	</div>
	<div id="admin">
		<a href="<%= root %>/admin/main.do" title="관리자 페이지로 이동">&reg;</a>
	</div>
	<div id="modal_back">
		<div id="modal">
		</div>
	</div>
</body>
</html>