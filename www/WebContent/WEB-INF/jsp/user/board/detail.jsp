<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<title><spring:message code='contents.board.detail' /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/user/board_detail.css">
<script type="text/javascript"  charset="UTF-8">
<!--
var flag = false;

function checkPasswd(id, value) {
	$.ajax({
		type: 'POST'
 		, dataType: 'JSON'  
 		, url: 'checkPasswd.do'
 		, data: { "id" : id }
 		, success: function(data) {
 			if(data.result.passwd == value) {
 				flag = true;
 			}
		}
		, error: function(data, status, err) {
			alert(err);
		}
	});
}

function goList() {
	var url = "listBoard.do?";
	url = url + "nowPage=${pageinfo.nowPage}";
	url = url + "&nowBlock=${pageinfo.nowBlock}";
	url = url + "&searchColumn=${pageinfo.searchColumn}";
	url = url + "&searchWord=${pageinfo.searchWord}";
	url = url + "&code=${pageinfo.code}";
	location.href = url;
}

function goUpdate() {
	var url = "updateBoard.do?id=${board.id}";
	url = url + "&nowPage=${pageinfo.nowPage}";
	url = url + "&nowBlock=${pageinfo.nowBlock}";
	url = url + "&searchColumn=${pageinfo.searchColumn}";
	url = url + "&searchWord=${pageinfo.searchWord}";
	url = url + "&code=${pageinfo.code}";
	location.href = url;
}

function goDelete() {
	openModal(300, 110);
	
	var html = "<ul>";
	html = html + "<li><spring:message code='messages.member.password' /></li>";
	html = html + "<li><input type='password' name='passwd' id='passwd' maxlength='20' required /></li>";
	html = html + "<li><button type='button' onclick='deleteProc()'><spring:message code='contents.common.confirm' /></button> ";
	html = html + "<button type='button' onclick='closeModal()'><spring:message code='contents.common.close' /></button></li>";
	html = html + "</ul>";
	
	$("#modal").html(html);
}

function goDetail(id) {
	var url = "detailBoard.do?id=" + id;
	url = url + "&nowPage=${pageinfo.nowPage}";
	url = url + "&nowBlock=${pageinfo.nowBlock}";
	url = url + "&searchColumn=${pageinfo.searchColumn}";
	url = url + "&searchWord=${pageinfo.searchWord}";
	url = url + "&code=${pageinfo.code}";
	location.href = url;
}

function deleteProc() {
	if($("#passwd").val() == "") {
		alert("<spring:message code='messages.member.password' />");
		$('input[name="passwd"]').focus();
		return;
	}
	
	checkPasswd("${board.id}", $("#passwd").val());
	
	if(flag == false) {
		alert("<spring:message code='messages.member.password.check' />");
		$("#passwd").val("");
		$('input[name="passwd"]').focus();
		return;
	}
	
	var url = "deleteBoard.do?id=${board.id}";
	url = url + "&userid=${board.userid}";
	url = url + "&filename=${board.filename}";
	url = url + "&nowPage=${pageinfo.nowPage}";
	url = url + "&nowBlock=${pageinfo.nowBlock}";
	url = url + "&searchColumn=${pageinfo.searchColumn}";
	url = url + "&searchWord=${pageinfo.searchWord}";
	url = url + "&code=${pageinfo.code}";
	location.href = url;
}

function validation(frm) {
	if(frm.contents.value == "") {
		alert("<spring:message code='messages.board.contents' />");
		frm.contents.focus();
		return false;
	}
	
	return true;
}

function openImage(width, height) {
	openModal(width+20, height+20);
	var html = "<div class='img'><a href='javascript:closeModal()'><img src='${pageContext.request.contextPath}/upload/board/${board.filename}' border='0' /></a></div>";
	$("#modal").html(html);
}
//-->
</script>
</head>
<body>
	<h1 class="title"><spring:message code='contents.board.detail' /></h1>
	<div class="detail">
		<ul>
			<li>${board.subject}</li>
			<li>${board.name} :: ${board.regdate} :: <spring:message code='contents.common.readcount' /> ${board.readcount}</li>
			<li>${contents}</li>
			<c:if test="${board.filename != null && board.filename != ''}">
			<li>
				<spring:message code="contents.board.filename" /> : <a href="javascript:openImage(${width}, ${height})">image</a>
			</li>
			</c:if>
			<c:if test="${board.filename == null || board.filename == ''}"><li><spring:message code="messages.board.file" /></li></c:if>
			<li>
				<c:if test="${(boardCode.write_auth == 'L' && board.userid == login.userid) || (boardCode.write_auth == 'A' && login.userid == 'administrator')}">
				<button type="button" onclick="goUpdate()"><spring:message code='contents.common.update' /></button>
				<button type="button" onclick="goDelete()"><spring:message code='contents.common.delete' /></button>
				</c:if>
				<button type="button" onclick="goList()"><spring:message code='contents.common.list' /></button>
			</li>
		</ul>
	</div>
	<c:if test="${login != null}">
	<div class="reply">
		<ul>
			<li>댓글 쓰기</li>
			<li>
				<form action="reply.do" method="post" onsubmit="return validation(this)">
					<fieldset>
						<input type="text" name="contents" required /> 
						<button type="submit"><spring:message code='contents.common.confirm' /></button>
					</fieldset>
				</form>
			</li>
		</ul>
	</div>
	</c:if>
	<div class="reply">
		<ul>
			<li>댓글 목록</li>
			<li>all the memories of hate and the lies</li>
			<li>don't you know eventually we'll pay the price</li>
			<li>all the hopes and the dreams will survive</li>
			<li>reunite we got to keep our faith alive</li>
		</ul>
	</div>
</body>
</html>