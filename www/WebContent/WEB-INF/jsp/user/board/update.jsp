<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<title><spring:message code='contents.board.update' /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/user/board_frm.css">
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

function validation(frm) {
	if(frm.subject.value == "") {
		alert("<spring:message code='messages.board.subject' />");
		frm.subject.focus();
		return false;
	}
	
	if(frm.contents.value == "") {
		alert("<spring:message code='messages.board.contents' />");
		frm.contents.focus();
		return false;
	}
	
	if(frm.passwd.value == "") {
		alert("<spring:message code='messages.member.password' />");
		frm.passwd.focus();
		return false;
	}
	
	checkPasswd("${board.id}", frm.passwd.value);
	
	if(flag == false) {
		alert("<spring:message code='messages.member.password.check' />");
		frm.passwd.value = "";
		frm.passwd.focus();
		return false;
	}
	
	return true;
}
//-->
</script>
</head>
<body>
	<h1 class="title"><spring:message code='contents.board.update' /></h1>
	<div id="frm">
		<form action="updateProc.do" method="post" enctype="multipart/form-data" onsubmit="return validation(this)">
			<fieldset>
				<legend>게시판 입력 폼</legend>
				<input type="hidden" name="id" value="${board.id}" />
				<input type="hidden" name="userid" value="${board.userid}" />
				<input type="hidden" name="name" value="${board.name}" />
				<input type="hidden" name="code" value="${pageinfo.code}" />
				<input type="hidden" name="nowPage" value="${pageinfo.nowPage}" />
				<input type="hidden" name="nowBlock" value="${pageinfo.nowBlock}" />
				<input type="hidden" name="searchColumn" value="${pageinfo.searchColumn}" />
				<input type="hidden" name="searchWord" value="${pageinfo.searchWord}" />
				<input type="hidden" name="filename" value="${board.filename}" />
				<table>
					<tr>
						<td><label for="board_subject"><spring:message code="contents.common.subject" /></label></td>
						<td><input type="text" id="board_subject" name="subject" maxlength="100" value="${board.subject}" required></td>
					</tr>
					<tr>
						<td><label for="board_contents"><spring:message code="contents.common.content" /></label></td>
						<td><textarea id="board_contents" name="contents" required>${board.contents}</textarea></td>
					</tr>
					<tr>
						<td><label for="board_passwd"><spring:message code="contents.member.password" /></label></td>
						<td><input type="password" id="board_passwd" name="passwd" maxlength="20" required></td>
					</tr>
					<tr>
						<td><label for="board_file"><spring:message code="contents.board.filename" /></label></td>
						<td>
							<input type="file" id="board_file" name="uploadFile">
							<c:if test="${board.filename != null && board.filename != ''}"><br /><spring:message code="contents.board.oldfilename" /> : ${board.filename}</c:if>
						</td>
					</tr>
				</table>
				<div class="btn"><button type="submit"><spring:message code='contents.common.confirm' /></button> <button type="reset"><spring:message code='contents.common.cancel' /></button></div>
			</fieldset>
		</form>
	</div>
</body>
</html>