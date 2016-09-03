<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="contents.cover.admin.title" /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/coverstory.css">
<script type="text/javascript" charset="UTF-8">
<!--
function deleteCover(id) {
	if(confirm("<spring:message code='messages.common.delete' />")) {
		var url = "deleteCover.do?id=" + id;
		location.href = url;
	}
}
//-->
</script>
</head>
<body>
	<h1><spring:message code="contents.cover.admin.title" /></h1>
	<div class="frm">
		<form action="insertCover.do" method="post">
			<fieldset>
				<legend>커버스토리 입력 폼</legend>
				<table>
					<tr>
						<td><label for="cover_subject"><spring:message code="contents.common.subject" /></label></td>
						<td><input type="text" id="cover_subject" name="subject" required></td>
					</tr>
					<tr>
						<td><label for="cover_contents"><spring:message code="contents.common.content" /></label></td>
						<td><textarea id="cover_contents" name="contents" required></textarea></td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="submit"><spring:message code="contents.common.confirm" /></button>
							<button type="reset"><spring:message code="contents.common.cancel" /></button>
						</td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
	<c:forEach var="row" items="${list}">
	<div class="list">
		<ul>
			<li>${row.subject}</li>
			<li>${row.regdate}</li>
			<li>${row.contents}</li>
			<li><button type="button" onclick="deleteCover(${row.id})"><spring:message code="contents.common.delete" /></button></li>
		</ul>
	</div>
	</c:forEach>
	
	<div class="paging">${paging}</div>
	
</body>
</html>