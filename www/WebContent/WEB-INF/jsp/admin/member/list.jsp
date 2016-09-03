<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code='contents.member.title4' /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/list.css">
<script type="text/javascript" charset="UTF-8">
<!--
var num = 0;
var userid = "";

function detailRow() {
	if(userid == "") {
		alert("수정할 회원 목록을 선택하시오.");
		return;
	}
	
	var url = "updateMember.do?userid=" + userid;
	url = url + "&nowPage=${pageinfo.nowPage}";
	url = url + "&nowBlock=${pageinfo.nowBlock}";
	url = url + "&searchColumn=${pageinfo.searchColumn}";
	url = url + "&searchWord=${pageinfo.searchWord}";
	url = url + "&code=${pageinfo.code}";
	location.href = url;
}

function selectRow(n, id) {
	$("#row" + num).css("background-color", "transparent");
	$("#row" + n).css("background-color", "#333");
	num = n;
	userid = id;
}

function deleteRow() {
	if(userid == "") {
		alert("삭제할 회원 목록을 선택하시오.");
		return;
	}
	
	if(confirm("<spring:message code='messages.common.delete' />")) {
		var url = "deleteMemberProc.do?userid=" + userid;
		url = url + "&nowPage=${pageinfo.nowPage}";
		url = url + "&nowBlock=${pageinfo.nowBlock}";
		url = url + "&searchColumn=${pageinfo.searchColumn}";
		url = url + "&searchWord=${pageinfo.searchWord}";
		url = url + "&code=${pageinfo.code}";
		location.href = url;
	}
}
//-->
</script>
</head>
<body>
	<h1><spring:message code='contents.member.title4' /></h1>
	<div class="search_form">
		<form action="listMember.do" method="get">
			<fieldset>
				<legend>검색 폼</legend>
				<select name="searchColumn">
					<option value="USERID"<c:if test="${pageinfo.searchColumn == 'USERID'}"> selected="selected"</c:if>><spring:message code='contents.member.id' /></option>
					<option value="USERNAME"<c:if test="${pageinfo.searchColumn == 'USERNAME'}"> selected="selected"</c:if>><spring:message code='contents.member.name' /></option>
				</select>
				<input type="search" name="searchWord" value="${pageinfo.searchWord}" required placeholder="<spring:message code='messages.common.search' />">
				<button type="submit"><spring:message code='contents.common.search' /></button>
			</fieldset>
		</form>
	</div>
	<div class="btn_control">
		<button type="button" onclick="detailRow()"><spring:message code='contents.common.update' /></button>
		<button type="button" onclick="deleteRow()"><spring:message code='contents.common.delete' /></button>
	</div>
	<div class="board">
		<div class="row_header">
			<ul>
				<li><spring:message code='contents.member.id' /></li>
				<li><spring:message code='contents.member.name' /></li>
				<li><spring:message code='contents.member.nickname' /></li>
				<li>접속날짜</li>
				<li>접속시간</li>
			</ul>
		</div>
		
		<c:if test="${count < 1}">
		<div class="rows"><spring:message code='messages.common.list.empty' /></div>
		</c:if>
		
		<c:if test="${count > 0}">
		
		<c:set var="num" value="1" />
			
		<c:forEach var="row" items="${list}">
		<div id="row${num}" class="rows" onmousedown="selectRow(${num}, '${row.userid}')">
			<ul>
				<li>${row.userid}</li>
				<li>${row.username}</li>
				<li>${row.nickname}</li>
				<li>${row.accedate}</li>
				<li>${row.accetime}</li>
			</ul>
		</div>
		<c:set var="num" value="${num + 1}" />
		</c:forEach>
		
		<div class="paging">${paging}</div>
		
		</c:if>
	</div>
</body>
</html>