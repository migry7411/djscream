<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code='contents.member.title2' /></title>
<script type="text/javascript">
<!--
function validation(frm) {	
	if(frm.username.value == "") {
		alert("<spring:message code='messages.member.name' />");
		frm.username.focus();
		return false;
	}
	
	if(frm.nickname.value == "") {
		alert("<spring:message code='messages.member.nickname' />");
		frm.nickname.focus();
		return false;
	}
	
	if(frm.password.value == "") {
		alert("<spring:message code='messages.member.password' />");
		frm.password.focus();
		return false;
	}
	
	if(frm.email.value == "") {
		alert("<spring:message code='messages.member.email' />");
		frm.email.focus();
		return false;
	}
	
	if(frm.phone.value == "") {
		alert("<spring:message code='messages.member.phone' />");
		frm.phone.focus();
		return false;
	}
	
	if(frm.birthdate.value == "" || frm.birthtype.value == "") {
		alert("<spring:message code='messages.member.birthdate' />");
		return false;
	}
	
	if(frm.sex.value == "") {
		alert("성별을 선택하세요.");
		return false;
	}
	
	return true;
}
//-->
</script>
</head>
<body>
	<h1><spring:message code='contents.member.title2' /></h1>
	<form action="updateProc.do" method="post" onsubmit="return validation(this)">
		<input type="hidden" name="userid" value="${member.userid}" />
		<input type="hidden" name="nowPage" value="${pageinfo.nowPage}" />
		<input type="hidden" name="nowBlock" value="${pageinfo.nowBlock}" />
		<input type="hidden" name="searchColumn" value="${pageinfo.searchColumn}" />
		<input type="hidden" name="searchWord" value="${pageinfo.searchWord}" />
		<input type="hidden" name="code" value="${pageinfo.code}" />
		<table>
			<colgroup>
				<col style="width:100px" />
				<col />
			</colgroup>
			<tr>
				<td><spring:message code='contents.member.id' /></td>
				<td>${member.userid}</td>
			</tr>
			<tr>
				<td><spring:message code='contents.member.name' /></td>
				<td><input type="text" name="username" value="${member.username}" /></td>
			</tr>
			<tr>
				<td><spring:message code='contents.member.nickname' /></td>
				<td><input type="text" name="nickname" value="${member.nickname}" /></td>
			</tr>
			<tr>
				<td><spring:message code='contents.member.password' /></td>
				<td><input type="password" name="password" value="${member.password}" /></td>
			</tr>
			<tr>
				<td><spring:message code='contents.member.email' /></td>
				<td><input type="email" name="email" value="${member.email}" /></td>
			</tr>
			<tr>
				<td><spring:message code='contents.member.phone' /></td>
				<td><input type="tel" name="phone" value="${member.phone}" pattern="^\d{2,3}-\d{3,4}-\d{4}$" /></td>
			</tr>
			<tr>
				<td><spring:message code='contents.member.birthdate' /></td>
				<td>
					<input type="date" name="birthdate" value="${member.birthdate}" />&nbsp;
					<input type="radio" name="birthtype" value="<spring:message code='contents.member.birthdate.solar' />" <c:if test="${member.birthtype == '양'}">checked="checked"</c:if>><spring:message code='contents.member.birthdate.solar' />&nbsp;
					<input type="radio" name="birthtype" value="<spring:message code='contents.member.birthdate.lunar' />" <c:if test="${member.birthtype == '음'}">checked="checked"</c:if>><spring:message code='contents.member.birthdate.lunar' />
				</td>
			</tr>
			<tr>
				<td><spring:message code='contents.member.sex' /></td>
				<td>
					<input type="radio" name="sex" value="<spring:message code='contents.member.sex.man' />" <c:if test="${member.sex == '남'}">checked="checked"</c:if>><spring:message code='contents.member.sex.man' />&nbsp;
					<input type="radio" name="sex" value="<spring:message code='contents.member.sex.woman' />" <c:if test="${member.sex == '여'}">checked="checked"</c:if>><spring:message code='contents.member.sex.woman' />
				</td>
			</tr>
			<tr>
				<td colspan="2"><button type="submit"><spring:message code='contents.common.confirm' /></button> <button type="reset"><spring:message code='contents.common.cancel' /></button></td>
			</tr>
		</table>
	</form>
</body>
</html>