<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML>
<html>
<head>
<title><spring:message code='contents.member.title1' /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/user/member_frm.css">
<script type="text/javascript">
<!--
var idcheck = false;
var nickCheck = false;
var emailCheck = false;

window.onload = function() {
	for(var i=1950; i<=2013; i++) {
		$("select[name=birthdate1]").append("<option value='" + i + "'>" + i + "</option>");
	}
	
	for(var i=1; i<=12; i++) {
		if(i < 10) {
			$("select[name=birthdate2]").append("<option value='0" + i + "'>0" + i + "</option>");
		} else {
			$("select[name=birthdate2]").append("<option value='" + i + "'>" + i + "</option>");
		}
	}
		
	for(var i=1; i<=31; i++) {
		if(i < 10) {
			$("select[name=birthdate3]").append("<option value='0" + i + "'>0" + i + "</option>");
		} else {
			$("select[name=birthdate3]").append("<option value='" + i + "'>" + i + "</option>");
		}
	}
};

function checkId() {
	var id = $("#userId").val();
	
	if(id == "") {
		alert("<spring:message code='messages.member.id' />");
		return;
	}
	
	$.ajax({
		type: 'POST'
 		, dataType: 'JSON'  
 		, url: 'checkId.do'
 		, data: { "userid" : id }
 		, success: function(data) {
 			if(data.result.userid == "") {
 				alert("<spring:message code='messages.member.id.check1' />");
 				idcheck = true;
 			} else {
 				alert("<spring:message code='messages.member.id.check2' />");
 				$("#userId").val("");
 				idcheck = false;
 			}
		}
		, error: function(data, status, err) {
			alert(err);
		}
	});
}

function checkNickname() {
	var nick = $("input:text[name=nickname]").val();
	
	if(nick == "") {
		alert("<spring:message code='messages.member.nickname' />");
		return;
	}
	
	$.ajax({
		type: 'POST'
 		, dataType: 'JSON'  
 		, url: 'checkNickname.do'
 		, data: { "nickname" : nick }
 		, success: function(data) {
 			if(data.result.userid == "") {
 				alert("<spring:message code='messages.member.nickname.check1' />");
 				nickCheck = true;
 			} else {
 				alert("<spring:message code='messages.member.nickname.check2' />");
 				$("input:text[name=nickname]").val("");
 				nickCheck = false;
 			}
		}
		, error: function(data, status, err) {
			alert(err);
		}
	});
}

function checkEmail() {
	var email1 = $("input:text[name=email1]").val();
	var email2 = $("input:text[name=email2]").val();
	var email = email1 + "@" + email2;
	
	if(email1 == "" || email2 == "") {
		alert("<spring:message code='messages.member.email' />");
		return;
	}
	
	$.ajax({
		type: 'POST'
 		, dataType: 'JSON'  
 		, url: 'checkEmail.do'
 		, data: { "email" : email }
 		, success: function(data) {
 			if(data.result.userid == "") {
 				alert("<spring:message code='messages.member.email.check1' />");
 				emailCheck = true;
 			} else {
 				alert("<spring:message code='messages.member.email.check2' />");
 				$("input:text[name=email1]").val("");
 				$("input:text[name=email2]").val("");
 				emailCheck = false;
 			}
		}
		, error: function(data, status, err) {
			alert(err);
		}
	});
}

function validation(frm) {
	if(frm.userid.value == "") {
		alert("<spring:message code='messages.member.id' />");
		frm.userid.focus();
		return false;
	}
	
	if(idcheck == false) {
		alert("<spring:message code='messages.member.id.check3' />");
		return false;
	}
	
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
	
	if(nickCheck == false) {
		alert("<spring:message code='messages.member.nickname.check3' />");
		return false;
	}
	
	if(frm.password.value == "") {
		alert("<spring:message code='messages.member.password' />");
		frm.password.focus();
		return false;
	}
	
	if(frm.password.value != frm.password.value) {
		alert("<spring:message code='messages.member.password.check' />");
		frm.password2.focus();
		return false;
	}
	
	if(frm.email1.value == "") {
		alert("<spring:message code='messages.member.email' />");
		frm.email1.focus();
		return false;
	}
	
	if(frm.email2.value == "") {
		alert("<spring:message code='messages.member.email' />");
		frm.email2.focus();
		return false;
	}
	
	if(emailCheck == false) {
		alert("<spring:message code='messages.member.email.check3' />");
		return false;
	}
	
	if(frm.phone1.value == "") {
		alert("<spring:message code='messages.member.phone' />");
		frm.phone1.focus();
		return false;
	}
	
	if(frm.phone2.value == "") {
		alert("<spring:message code='messages.member.phone' />");
		frm.phone2.focus();
		return false;
	}
	
	if(frm.phone3.value == "") {
		alert("<spring:message code='messages.member.phone' />");
		frm.phone3.focus();
		return false;
	}
	
	if(frm.birthdate1.value == "") {
		alert("<spring:message code='messages.member.birthdate' />");
		frm.birthdate1.focus();
		return false;
	}
	
	if(frm.birthdate2.value == "") {
		alert("<spring:message code='messages.member.birthdate' />");
		frm.birthdate2.focus();
		return false;
	}
	
	if(frm.birthdate3.value == "") {
		alert("<spring:message code='messages.member.birthdate' />");
		frm.birthdate3.focus();
		return false;
	}
	
	frm.email.value = frm.email1.value + "@" + frm.email2.value;
	frm.phone.value = frm.phone1.value + "-" + frm.phone2.value + "-" + frm.phone3.value;
	frm.birthdate.value = frm.birthdate1.value + "-" + frm.birthdate2.value + "-" + frm.birthdate3.value;
	
	return true;
}

//숫자만 입력
function check_number(){
	if( !( (event.keyCode >= 37 && event.keyCode<=57) || (event.keyCode >= 96 && event.keyCode <= 105) || event.keyCode == 8  || event.keyCode == 9)  ){
		event.returnValue=false;
	}
}
//-->
</script>
</head>
<body>
	<h1 class="title"><spring:message code='contents.member.title1' /></h1>
	<div id="frm">
		<form action="insertProc.do" method="post" onsubmit="return validation(this)">
			<table>
				<colgroup>
					<col style="width:120px" />
					<col />
				</colgroup>
				<tr>
					<td><spring:message code='contents.member.id' /></td>
					<td>
						<input type="text" name="userid" id="userId" maxlength="15" />
						<button type="button" onclick="checkId()"><spring:message code='contents.member.check' /></button>
					</td>
				</tr>
				<tr>
					<td><spring:message code='contents.member.name' /></td>
					<td><input type="text" name="username" maxlength="10" /></td>
				</tr>
				<tr>
					<td><spring:message code='contents.member.nickname' /></td>
					<td>
						<input type="text" name="nickname" maxlength="10" />
						<button type="button" onclick="checkNickname()"><spring:message code='contents.member.check' /></button>
					</td>
				</tr>
				<tr>
					<td><spring:message code='contents.member.password' /></td>
					<td><input type="password" name="password" maxlength="20" /></td>
				</tr>
				<tr>
					<td><spring:message code='contents.member.password.confirm' /></td>
					<td><input type="password" name="password2" maxlength="20" /></td>
				</tr>
				<tr>
					<td><spring:message code='contents.member.email' /></td>
					<td>
						<input type="text" name="email1"  size="15" />@<input type="text" name="email2"  size="20" />
						<button type="button" onclick="checkEmail()"><spring:message code='contents.member.check' /></button>
						<input type="hidden" name="email" /><br />
						<span><spring:message code="messages.member.email.remark" /></span>
					</td>
				</tr>
				<tr>
					<td><spring:message code='contents.member.phone' /></td>
					<td>
						<input type="text" name="phone1" maxlength="3" size="3" onkeydown="check_number()" />-<input type="text" name="phone2" maxlength="4" size="4" onkeydown="check_number()"  />-<input type="text" name="phone3" maxlength="4" size="4" onkeydown="check_number()"  />
						<input type="hidden" name="phone" />
					</td>
				</tr>
				<tr>
					<td><spring:message code='contents.member.birthdate' /></td>
					<td>
						<select name="birthdate1"><option value="">----</option></select><spring:message code='contents.member.birthdate.year' />
						<select name="birthdate2"><option value="">--</option></select><spring:message code='contents.member.birthdate.month' />
						<select name="birthdate3"><option value="">--</option></select><spring:message code='contents.member.birthdate.day' />&nbsp;
						<input type="hidden" name="birthdate" />
						<input type="radio" name="birthtype" value="<spring:message code='contents.member.birthdate.solar' />" checked="checked"><spring:message code='contents.member.birthdate.solar' /> <input type="radio" name="birthtype" value="<spring:message code='contents.member.birthdate.lunar' />"><spring:message code='contents.member.birthdate.lunar' />
					</td>
				</tr>
				<tr>
					<td><spring:message code='contents.member.sex' /></td>
					<td>
						<input type="radio" name="sex" value="<spring:message code='contents.member.sex.man' />" checked="checked"><spring:message code='contents.member.sex.man' />&nbsp;
						<input type="radio" name="sex" value="<spring:message code='contents.member.sex.woman' />"><spring:message code='contents.member.sex.woman' />
					</td>
				</tr>
			</table>
			<div class="btn"><button type="submit"><spring:message code='contents.common.confirm' /></button> <button type="reset"><spring:message code='contents.common.cancel' /></button></div>
		</form>
	</div>
</body>
</html>