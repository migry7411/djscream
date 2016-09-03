<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>${boardCode.name}</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/list.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/user/board_list.css">
<script type="text/javascript" charset="UTF-8">
<!--
function detailRow(id) {
	var url = "detailBoard.do?id=" + id;
	url = url + "&nowPage=${pageinfo.nowPage}";
	url = url + "&nowBlock=${pageinfo.nowBlock}";
	url = url + "&searchColumn=${pageinfo.searchColumn}";
	url = url + "&searchWord=${pageinfo.searchWord}";
	url = url + "&code=${pageinfo.code}";
	location.href = url;
}

function insertRow() {
	var url = "insertBoard.do?code=${pageinfo.code}";
	location.href = url;
}
//-->
</script>
</head>
<body>
	<h1 class="title">${boardCode.name}</h1>
	<div class="search_form">
		<form action="listBoard.do" method="get">
			<input type="hidden" name="nowPage" value="${pageinfo.nowPage}" />
			<input type="hidden" name="nowBlock" value="${pageinfo.nowBlock}" />
			<input type="hidden" name="code" value="${pageinfo.code}" />
			<fieldset>
				<legend>검색 폼</legend>
				<select name="searchColumn">
					<option value="NAME"<c:if test="${pageinfo.searchColumn == 'NAME'}"> selected="selected"</c:if>><spring:message code='contents.member.name' /></option>
					<option value="SUBJECT"<c:if test="${pageinfo.searchColumn == 'SUBJECT'}"> selected="selected"</c:if>><spring:message code='contents.common.subject' /></option>
					<option value="CONTENTS"<c:if test="${pageinfo.searchColumn == 'CONTENTS'}"> selected="selected"</c:if>><spring:message code='contents.common.content' /></option>
				</select>
				<input type="search" name="searchWord" value="${pageinfo.searchWord}" required placeholder="<spring:message code='messages.common.search' />">
				<button type="submit"><spring:message code='contents.common.search' /></button>
			</fieldset>
		</form>
	</div>
	<div class="btn_control">
		<c:if test="${(boardCode.write_auth == 'L' && login != null) || (boardCode.write_auth == 'A' && login.userid == 'administrator')}">
		<button type="button" onclick="insertRow()"><spring:message code='contents.common.insert' /></button>
		</c:if>
	</div>
	<div class="board">
		<div class="row_header">
			<ul>
				<li><spring:message code='contents.common.num' /></li>
				<li><spring:message code='contents.common.subject' /></li>
				<li><spring:message code='contents.member.name' /></li>
				<li><spring:message code='contents.common.regdate' /></li>
				<li><spring:message code='contents.common.readcount' /></li>
			</ul>
		</div>
		<c:if test="${count < 1}">
		<div class="rows_empty"><spring:message code='messages.common.list.empty' /></div>
		</c:if>
		<c:if test="${count > 0}">
		<c:set var="num" value="${recNo}" />
		<c:forEach var="row" items="${list}">
		<div class="rows" onmousedown="detailRow('${row.id}')">
			<ul>
				<li>${num}</li>
				<li>${row.subject}</li>
				<li>${row.name}</li>
				<li>${row.regdate}</li>
				<li>${row.readcount}</li>
			</ul>
		</div>
		<c:set var="num" value="${num - 1}" />
		</c:forEach>
		<div class="paging">${paging}</div>
		</c:if>
	</div>
</body>
</html>