<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code='contents.main.title' /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/user/main.css">
</head>
<body>
				<div class="main_wrapper">
					<section class="cover_story">
						<h1><spring:message code='contents.main.cover' /></h1>
						<h2>${coverTitle}</h2>
						<time>${coverDate}</time>
						<p>${coverContents}</p>
					</section>
					<section class="latest_board">
						<h1><spring:message code='contents.main.board' /></h1>
						<ul>
						<c:if test="${fn:length(boardList) < 1}">
							<li class="row_empty"><spring:message code='messages.common.list.empty' /></li>
						</c:if>
						<c:if test="${fn:length(boardList) > 0}">
						<c:forEach var="row" items="${boardList}">
							<li><a href="${pageContext.request.contextPath}/user/board/detailBoard.do?id=${row.id}&nowPage=0&nowBlock=0&searchColumn=&searchWord=&code=${row.code}">${row.subject}</a><time>${row.regdate}</time></li>
						</c:forEach>
						</c:if>
						</ul>
					</section>
					<section class="new_music">
						<h1><spring:message code='contents.main.music' /></h1>
						<ul>
							<li><a href="#">임땅덩 - 소주 한 잔</a><time>2013-07-12</time></li>
							<li><a href="#">김범수 - 늪</a><time>2013-07-12</time></li>
							<li><a href="#">로이킴 - 봄봄봄</a><time>2013-07-12</time></li>
							<li><a href="#">노홍철 - 노가르시아</a><time>2013-07-12</time></li>
							<li><a href="#">최종훈 - 말년에 모태솔로라니...</a><time>2013-07-12</time></li>
						</ul>
					</section>
				</div>
</body>
</html>