<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML>
<html>
<head>
<title><spring:message code='contents.board.write' /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/user/board_frm.css">
<script type="text/javascript"  charset="UTF-8">
<!--
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
	
	return true;
}
//-->
</script>
</head>
<body>
	<h1 class="title"><spring:message code='contents.board.write' /></h1>
	<div id="frm">
		<form action="insertProc.do" method="post" enctype="multipart/form-data" onsubmit="return validation(this)">
			<fieldset>
				<legend>게시판 입력 폼</legend>
				<input type="hidden" name="userid" value="${login.userid}" />
				<input type="hidden" name="name" value="${login.nickname}" />
				<input type="hidden" name="code" value="${code}" />
				<table>
					<tr>
						<td><label for="board_subject"><spring:message code="contents.common.subject" /></label></td>
						<td><input type="text" id="board_subject" name="subject" maxlength="100" required></td>
					</tr>
					<tr>
						<td><label for="board_contents"><spring:message code="contents.common.content" /></label></td>
						<td><textarea id="board_contents" name="contents" required></textarea></td>
					</tr>
					<tr>
						<td><label for="board_passwd"><spring:message code="contents.member.password" /></label></td>
						<td><input type="password" id="board_passwd" name="passwd" maxlength="20" required></td>
					</tr>
					<tr>
						<td><label for="board_file"><spring:message code="contents.board.filename" /></label></td>
						<td><input type="file" id="board_file" name="uploadFile"></td>
					</tr>
				</table>
				<div class="btn"><button type="submit"><spring:message code='contents.common.confirm' /></button> <button type="reset"><spring:message code='contents.common.cancel' /></button></div>
			</fieldset>
		</form>
	</div>
</body>
</html>