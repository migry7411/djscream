<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>update</title>
<script type="text/javascript" charset="UTF-8">
<!--
window.onload = function() {
	var flag = "${checkPasswd}";
	
	if(flag == "Y") {
		alert("<spring:message code='messages.member.update' />");
		var url = "listMember.do";
		url = url + "?nowPage=${pageinfo.nowPage}";
		url = url + "&nowBlock=${pageinfo.nowBlock}";
		url = url + "&searchColumn=${pageinfo.searchColumn}";
		url = url + "&searchWord=${pageinfo.searchWord}";
		url = url + "&code=${pageinfo.code}";
		location.href = url;
	} else {
		alert("<spring:message code='messages.member.password.check' />");
		history.back();
	}
};
//-->
</script>
</head>
<body>

</body>
</html>